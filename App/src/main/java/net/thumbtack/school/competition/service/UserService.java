package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import net.thumbtack.school.competition.daoimpl.UserDaoImpl;
import net.thumbtack.school.competition.dto.request.user.LoginDtoRequest;
import net.thumbtack.school.competition.dto.response.user.DeleteUserDtoResponse;
import net.thumbtack.school.competition.dto.response.user.LoginDtoResponse;
import net.thumbtack.school.competition.dto.response.user.LogoutDtoResponse;
import net.thumbtack.school.competition.exception.ServerErrorCode;
import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.mapper.UserMapper;
import net.thumbtack.school.competition.model.User;
import net.thumbtack.school.competition.server.ServerResponse;

public class UserService {
    private final UserDaoImpl userDao = new UserDaoImpl();
    private final Gson gson = new Gson();

    public ServerResponse loginUser(String requestJsonString) {
        try {
            LoginDtoRequest loginDtoRequest = ServiceBaseThings.getClassFromJson(requestJsonString, LoginDtoRequest.class);
            validateLogin(loginDtoRequest);
            User user = UserMapper.INSTANSE.userDtoForUser(loginDtoRequest);

            String token = userDao.loginUser(user);
            LoginDtoResponse loginDtoResponse = new LoginDtoResponse(token);
            return new ServerResponse(loginDtoResponse);
        }catch (ServiceException e){
            return new ServerResponse(e);
        }
    }

    private static void validateLogin(LoginDtoRequest loginDtoRequest) throws ServiceException {
        if(loginDtoRequest.getLogin() == null || loginDtoRequest.getLogin().equals("")){
            throw new ServiceException(ServerErrorCode.EMPTY_LOGIN);
        }
        if(loginDtoRequest.getPassword() == null || loginDtoRequest.getPassword().equals("")){
            throw new ServiceException(ServerErrorCode.EMPTY_PASSWORD);
        }
    }

    public ServerResponse logoutUser(String token){
        try {
            if(token.equals("")){
                throw new ServiceException(ServerErrorCode.WRONG_TOKEN);
            }
            userDao.logoutUser(token);
            LogoutDtoResponse logoutDtoResponse = new LogoutDtoResponse(token);
            return new ServerResponse(logoutDtoResponse);
        }catch (ServiceException e){
            return new ServerResponse(e);
        }
    }

    public ServerResponse leaveServer(String token){
        try{
            if(token.equals("")){
                throw new ServiceException(ServerErrorCode.WRONG_TOKEN);
            }
            userDao.leaveServer(token);
            DeleteUserDtoResponse deleteDtoResponse = new DeleteUserDtoResponse();
            return new ServerResponse(deleteDtoResponse);
        } catch (ServiceException e) {
            return new ServerResponse(e);
        }
    }


}
