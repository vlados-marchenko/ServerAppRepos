package net.thumbtack.school.competition.server;

import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.service.*;

public class Server {
    private final ExpertService expertService = new ExpertService();
    private final ParticipantService participantService = new ParticipantService();
    private final GrantApplicationService grantApplicationService = new GrantApplicationService();
    private final UserService userService = new UserService();


    public ServerResponse registerExpert(String requestJsonString) throws ServiceException {
        return expertService.registerExpert(requestJsonString);
    }

    public ServerResponse registerParticipant(String requestJsonString) throws ServiceException{
        return participantService.registerParticipant(requestJsonString);
    }

    public ServerResponse loginUser(String requestJsonString) throws ServiceException {
        return userService.loginUser(requestJsonString);
    }

    public ServerResponse logoutUser(String token){
        return userService.logoutUser(token);
    }

    public ServerResponse leaveServer(String token){
        return userService.leaveServer(token);
    }



    //public ServerResponse methodName(String token, String requestJsonString);


//        Участник добавляет новую заявку на сервер. requestJsonString содержит
//    описание заявки. При успешном выполнении ServerResponse содержит
//    responseCode 200, а responseData - то, что Вы найдет нужным вернуть как
//    результат выполнения операции. При неудаче ServerResponse содержит
//    responseCode 400, а responseData - json с полем “error”.

    public ServerResponse addApplication(String requestJsonString) throws ServiceException {
        return grantApplicationService.addApplication(requestJsonString);
    }

    public ServerResponse markingApplicationByExpert(String token, String jsonString) throws ServiceException {
        return expertService.setMarkForApplication(token, jsonString);
    }

//
//        Эксперт получает список заявок. requestJsonString содержит параметры
//    для поиска заявок. При успешном выполнении ServerResponse содержит
//    responseCode 200, а responseData - json с информацией о заявках. При неудаче
//    ServerResponse содержит responseCode 400, а responseData - json с полем
//    “error”.
//    public ServerResponse getApplications(String token, String requestJsonString){
//
//    }

}
