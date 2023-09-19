package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import net.thumbtack.school.competition.daoimpl.GrantApplicationDaoImpl;
import net.thumbtack.school.competition.dto.request.application.AddApplicationDtoRequest;
import net.thumbtack.school.competition.dto.response.application.AddApplicationDtoResponse;
import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.model.GrantApplication;
import net.thumbtack.school.competition.exception.ServerErrorCode;
import net.thumbtack.school.competition.server.ServerResponse;


public class GrantApplicationService {

    private final GrantApplicationDaoImpl grantApplicationDao = new GrantApplicationDaoImpl();
    private Gson gson = new Gson();

    public ServerResponse addApplication(String jsonString) throws ServiceException {
        try{
            AddApplicationDtoRequest addApplicationDtoRequest = gson.fromJson(jsonString, AddApplicationDtoRequest.class);
            validateApplication(addApplicationDtoRequest);
            GrantApplication grantApplication = new GrantApplication(addApplicationDtoRequest.getApplicationName(),
                    addApplicationDtoRequest.getApplicationDescription(),addApplicationDtoRequest.getListOfDirections(),
                    addApplicationDtoRequest.getSumOfGrant(), 0 );
            grantApplicationDao.addApplication(grantApplication);
            AddApplicationDtoResponse addApplicationDtoResponse = new AddApplicationDtoResponse("Success");
            return new ServerResponse(addApplicationDtoResponse);
        } catch (ServiceException e) {
            return new ServerResponse(e);
        }
    }


    private static void validateApplication(AddApplicationDtoRequest addApplicationDtoRequest) throws ServiceException{
        if(addApplicationDtoRequest.getApplicationName() == null || addApplicationDtoRequest.getApplicationName().equals("")){
            throw new ServiceException(ServerErrorCode.EMPTY_APPLICATION_NAME);
        }
        if(addApplicationDtoRequest.getApplicationDescription() == null || addApplicationDtoRequest.getApplicationDescription().equals("")){
            throw new ServiceException(ServerErrorCode.EMPTY_APPLICATION_DESCRIPTION);
        }
        if(addApplicationDtoRequest.getListOfDirections() == null || addApplicationDtoRequest.getListOfDirections().isEmpty()){
            throw new ServiceException(ServerErrorCode.EMPTY_LIST_OF_DIRECTIONS);
        }
        if(addApplicationDtoRequest.getSumOfGrant() == 0 || addApplicationDtoRequest.getSumOfGrant() < 0){
            throw new ServiceException(ServerErrorCode.EMPTY_SUM_OF_GRANT);
        }
    }

//    public ServerResponse deleteApplication(){
//
//    }
}
