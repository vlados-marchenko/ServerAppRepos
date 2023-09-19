package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.dao.GrantApplicationDao;
import net.thumbtack.school.competition.database.DataBase;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.GrantApplication;
import net.thumbtack.school.competition.exception.ServiceException;

public class GrantApplicationDaoImpl implements GrantApplicationDao {
    private DataBase dataBase = DataBase.getDataBase();
    @Override
    public void addApplication(GrantApplication grantApplication) throws ServiceException {
        dataBase.insertApplicationToOther(grantApplication);
    }

    @Override
    public void deleteApplication(GrantApplication grantApplication) throws ServiceException{
        dataBase.deleteApplication(grantApplication);
    }

    @Override
    public void markingApplicationByExpert(Expert expert, int mark, GrantApplication application){
        dataBase.markingApplicationByExpert(expert, mark, application);
    }
}
