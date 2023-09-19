package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dao.GrantApplicationDao;
import net.thumbtack.school.competition.dao.UserDao;
import net.thumbtack.school.competition.daoimpl.GrantApplicationDaoImpl;
import net.thumbtack.school.competition.daoimpl.UserDaoImpl;
import net.thumbtack.school.competition.dto.request.expert.ExpertMarkingApplicationDtoRequest;
import net.thumbtack.school.competition.dto.request.expert.RegisterExpertDtoRequest;
import net.thumbtack.school.competition.dto.response.application.AddApplicationDtoResponse;
import net.thumbtack.school.competition.dto.response.application.ApplicationDtoResponse;
import net.thumbtack.school.competition.dto.response.expert.RegisterExpertDtoResponse;
import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.mapper.ExpertMapper;
import net.thumbtack.school.competition.mapper.GrantApplicationMapper;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.exception.ServerErrorCode;
import net.thumbtack.school.competition.model.GrantApplication;
import net.thumbtack.school.competition.model.User;
import net.thumbtack.school.competition.server.ServerResponse;


public class ExpertService extends ServiceBaseThings{
    private final UserDao userDao = new UserDaoImpl();
    private final GrantApplicationDao grantApplicationDao = new GrantApplicationDaoImpl();
    private Gson gson = new Gson();

    public ServerResponse registerExpert(String jsonString) {
        try {
            RegisterExpertDtoRequest registerExpertDtoRequest = getClassFromJson(jsonString, RegisterExpertDtoRequest.class);
            validateExpert(registerExpertDtoRequest);

            Expert expert = ExpertMapper.INSTANSE.expertDtoForExpert(registerExpertDtoRequest);
            userDao.insertUser(expert);
            RegisterExpertDtoResponse registerExpertDtoResponse = new RegisterExpertDtoResponse();
            return new ServerResponse(registerExpertDtoResponse);
        } catch (ServiceException e) {
            return new ServerResponse(e);
        }
    }

    private static void validateExpert(RegisterExpertDtoRequest registerExpertDtoRequest) throws ServiceException {
        if (registerExpertDtoRequest.getFirstName() == null || registerExpertDtoRequest.getFirstName().equals("")) {
            throw new ServiceException(ServerErrorCode.EMPTY_FIRSTNAME);
        }
        if (registerExpertDtoRequest.getLastName() == null || registerExpertDtoRequest.getLastName().equals("")) {
            throw new ServiceException(ServerErrorCode.EMPTY_LASTNAME);
        }
        if (registerExpertDtoRequest.getListExpertOfDirections() == null || registerExpertDtoRequest.getListExpertOfDirections().isEmpty()) {
            throw new ServiceException(ServerErrorCode.EMPTY_LIST_OF_DIRECTIONS);
        }
        if (registerExpertDtoRequest.getLogin() == null || registerExpertDtoRequest.getLogin().equals("")) {
            throw new ServiceException(ServerErrorCode.EMPTY_LOGIN);
        }
        if (registerExpertDtoRequest.getPassword() == null || registerExpertDtoRequest.getPassword().equals("")) {
            throw new ServiceException(ServerErrorCode.EMPTY_PASSWORD);
        }
    }

    public ServerResponse setMarkForApplication(String token, String jsonString) throws ServiceException {
       try{
           validateData(token, jsonString);
           ExpertMarkingApplicationDtoRequest applicationDtoRequest = ServiceBaseThings.getClassFromJson(jsonString, ExpertMarkingApplicationDtoRequest.class);

           if(applicationDtoRequest.getMark() <=0 || applicationDtoRequest.getMark() >5){
               throw new ServiceException(ServerErrorCode.WRONG_APPLICATION_MARK);
           }
           User user = userDao.getLoggedUser(token);
           ServiceBaseThings.validateUserBelongingToClass(user, Expert.class);

           GrantApplication application = GrantApplicationMapper.INSTANCE.applicationDtoForApplication(applicationDtoRequest.getApplicationDtoRequest());
           grantApplicationDao.markingApplicationByExpert((Expert) user, applicationDtoRequest.getMark(), application);

           ApplicationDtoResponse resp = new ApplicationDtoResponse("Application marked successfully");
           return new ServerResponse(resp);
       }catch (ServiceException e){
           return new ServerResponse(e);
       }
    }

    private static void validateData(String jsonString, String token) throws ServiceException {
        if(jsonString.equals("")){
            throw new ServiceException(ServerErrorCode.WRONG_JSON);
        }
        if(token.equals("")){
            throw new ServiceException(ServerErrorCode.WRONG_TOKEN);
        }

    }



}
