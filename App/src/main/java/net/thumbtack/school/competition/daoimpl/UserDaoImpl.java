package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.dao.UserDao;
import net.thumbtack.school.competition.database.DataBase;
import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.model.User;


public class UserDaoImpl implements UserDao {
    private DataBase dataBase = DataBase.getDataBase();

    @Override
    public void insertUser(User user) throws ServiceException{
        dataBase.insertUser(user);
    }
    @Override
    public String loginUser(User user) throws ServiceException{
        return dataBase.addToken(user);
    }

    @Override
    public void logoutUser(String token) throws ServiceException{
        dataBase.logoutUser(token);
    }

    @Override
    public void leaveServer(String token) throws ServiceException{
        dataBase.leaveServer(token);
    }

    @Override
    public User getLoggedUser(String token){
        return dataBase.getLoggedUser(token);
    }
}
