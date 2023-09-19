package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.competition.exception.ServerErrorCode;
import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.model.User;

public abstract class ServiceBaseThings {
    static <T> T getClassFromJson(String jsonString, Class<T> typeOfClass) throws ServiceException {
        try{
            Gson gson = new Gson();
            return gson.fromJson(jsonString, typeOfClass);
        }catch (JsonSyntaxException e){
            throw new ServiceException(ServerErrorCode.WRONG_JSON);
        }
    }

    static <T> void validateUserBelongingToClass(User user, Class<T> typeOfClass) throws ServiceException{
        if(user == null){
            throw new ServiceException(ServerErrorCode.NOT_FOUND);
        }
        if(!user.getClass().equals(typeOfClass)) {
            throw new ServiceException(ServerErrorCode.USER_IS_NOT_BELONG_TO_CLASS);
        }
    }
}
