package net.thumbtack.school.competition.database;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.GrantApplication;
import net.thumbtack.school.competition.model.Participant;
import net.thumbtack.school.competition.model.User;
import net.thumbtack.school.competition.exception.ServerErrorCode;
import net.thumbtack.school.competition.exception.ServiceException;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.*;

public class DataBase {
    private int id = 1;
    private static DataBase dataBase;
    private final Map<Integer, User> registeredUsers;
    private final Map<Integer, GrantApplication> applications;

    private final Map<String, User> users = new HashMap<>();
    private final BidiMap<String, User> tokens;

    private final MultiValuedMap<Participant, GrantApplication> participantApplications; //participant, applications
    private final Map<Expert, Map<GrantApplication, Integer>> expertAndApplications;
    //private final Map<String, Map<String, Integer>> markedApplications; //expert's login, map<application name, expert's mark>


    private DataBase() {
        registeredUsers = new HashMap<>();
        applications = new HashMap<>();
        tokens = new DualHashBidiMap<>();
        participantApplications = new ArrayListValuedHashMap<>();
        expertAndApplications = new HashMap<>();
//        markedApplications = new HashMap<>();
    }

    public static DataBase getDataBase() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }


    public void insertUser(User user) throws ServiceException{
        if(users.putIfAbsent(user.getLogin(), user) != null){
            throw new ServiceException(ServerErrorCode.LOGIN_IS_ALREADY_USES);
        }
        user.setId(id);
        registeredUsers.put(user.getId(), user);
        id++;
    }

    public String addToken(User user) {
       String token = tokens.getKey(user);

       if(token == null){
           token = UUID.randomUUID().toString();
           tokens.put(token, users.get(user.getLogin()));
       }
       return token;
    }


    public void logoutUser(String token) throws ServiceException{
        if(tokens.remove(token) == null) {
            throw new ServiceException(ServerErrorCode.USER_IS_NOT_LOGGED_IN);
        }
    }


    public void insertApplicationToOther(GrantApplication grantApplication) {
        grantApplication.setId(id);
        applications.put(grantApplication.getId(), grantApplication);
        participantApplications.put(grantApplication.getParticipant(), grantApplication);
        id++;
    }

    public void deleteApplication(GrantApplication grantApplication) throws ServiceException{
        Collection collectedApplication = participantApplications.get(grantApplication.getParticipant());
        if(!collectedApplication.contains(grantApplication)){
            throw new ServiceException(ServerErrorCode.GRANT_APPLICATION_IS_NOT_FOUND);
        }
        participantApplications.removeMapping(grantApplication.getParticipant(), grantApplication);
    }

    public User getLoggedUser(String token){
        return tokens.get(token);
    }

    public void markingApplicationByExpert(Expert expert, int mark, GrantApplication application){
        Map<GrantApplication, Integer> applicationAndMark = expertAndApplications.get(expert);
        if(applicationAndMark == null){
            applicationAndMark = new HashMap<>();
        }
        applicationAndMark.put(application, mark);
        expertAndApplications.put(expert, applicationAndMark);
    }

    public void leaveServer(String token) throws ServiceException{
        User volunteerToDeleting = tokens.remove(token);
        if(volunteerToDeleting == null){
            throw new ServiceException(ServerErrorCode.USER_IS_NOT_LOGGED_IN);
        }
        registeredUsers.remove(volunteerToDeleting);
        //удаление заявок, если чел является участником
        //удаление оценок, если чел является экспертом
        if(volunteerToDeleting.getClass().equals(Expert.class)){
            expertAndApplications.remove(volunteerToDeleting);
        }
    }




}
