package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.GrantApplication;
import net.thumbtack.school.competition.exception.ServiceException;

public interface GrantApplicationDao {
    void addApplication(GrantApplication grantApplication) throws ServiceException;
    void deleteApplication(GrantApplication grantApplication) throws ServiceException;

    void markingApplicationByExpert(Expert expert, int mark, GrantApplication application);
}
