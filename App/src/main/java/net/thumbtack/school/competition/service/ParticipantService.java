package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dao.UserDao;
import net.thumbtack.school.competition.daoimpl.UserDaoImpl;
import net.thumbtack.school.competition.dto.request.participant.RegisterParticipantDtoRequest;
import net.thumbtack.school.competition.dto.response.participant.RegisterParticipantDtoResponse;
import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.mapper.ParticipantMapper;
import net.thumbtack.school.competition.model.Participant;
import net.thumbtack.school.competition.exception.ServerErrorCode;
import net.thumbtack.school.competition.server.ServerResponse;


public class ParticipantService extends ServiceBaseThings{
    private final UserDao userDao = new UserDaoImpl();
    private Gson gson = new Gson();

    public ServerResponse registerParticipant(String requestJsonString) {
        try {
            RegisterParticipantDtoRequest registerParticipantDtoRequest = getClassFromJson(requestJsonString, RegisterParticipantDtoRequest.class);
            validateParticipant(registerParticipantDtoRequest);

            Participant participant = ParticipantMapper.INSTANSE.participantDtoForParticipant(registerParticipantDtoRequest);
            userDao.insertUser(participant);
            RegisterParticipantDtoResponse registerParticipantDtoResponse = new RegisterParticipantDtoResponse();
            return new ServerResponse(registerParticipantDtoResponse);
        }catch (ServiceException e){
            return new ServerResponse(e);
        }
    }

    private static void validateParticipant(RegisterParticipantDtoRequest registerParticipantDtoRequest) throws ServiceException {
        if(registerParticipantDtoRequest.getFirstName() == null || registerParticipantDtoRequest.getFirstName().equals("")){
            throw new ServiceException(ServerErrorCode.EMPTY_FIRSTNAME);
        }
        if(registerParticipantDtoRequest.getLastName() == null || registerParticipantDtoRequest.getLastName().equals("")){
            throw new ServiceException(ServerErrorCode.EMPTY_LASTNAME);
        }
        if(registerParticipantDtoRequest.getCompanyName() == null || registerParticipantDtoRequest.getCompanyName().equals("")){
            throw new ServiceException(ServerErrorCode.EMPTY_COMPANY_NAME);

        }
        if(registerParticipantDtoRequest.getLogin() == null || registerParticipantDtoRequest.getLogin().equals("")){
            throw new ServiceException(ServerErrorCode.EMPTY_LOGIN);
        }
        if(registerParticipantDtoRequest.getPassword() == null || registerParticipantDtoRequest.getPassword().equals("")){
            throw new ServiceException(ServerErrorCode.EMPTY_PASSWORD);
        }

    }

}
