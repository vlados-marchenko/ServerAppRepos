package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.model.User;

public interface UserDao{
    void insertUser(User user) throws ServiceException;
    String loginUser(User user) throws ServiceException;
    void logoutUser(String token) throws ServiceException;
    void leaveServer(String token) throws ServiceException;
    User getLoggedUser(String token);

}
