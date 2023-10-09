package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dto.request.participant.RegisterParticipantDtoRequest;
import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.server.Server;
import net.thumbtack.school.competition.server.ServerResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParticipantService {
    private final Gson gson = new Gson();
    private final Server server = new Server();

    @Test
    public void testRegisterParticipant() throws ServiceException {
        RegisterParticipantDtoRequest participantDtoRequest = new RegisterParticipantDtoRequest("Alex", "Thompson",
                "Quarter To", "ThAlex", "Password1234");
        ServerResponse response = server.registerParticipant(gson.toJson(participantDtoRequest));
        assertEquals(200, response.getResponseCode());
        assertEquals("{}", response.getResponseData());

        RegisterParticipantDtoRequest participantDtoRequest1 = new RegisterParticipantDtoRequest("Alexandr", "Grill",
                "Quarter To", "ThAlex", "Qwerty1994");
        response = server.registerParticipant(gson.toJson(participantDtoRequest1));
        assertEquals(400, response.getResponseCode());
        System.out.println(response.getResponseData());

    }

    @Test
    public void testRegisterCopyParticipant() throws ServiceException {
        RegisterParticipantDtoRequest participantDtoRequest = new RegisterParticipantDtoRequest("Alex", "Thompson",
                "Quarter To", "ThAlexandr", "Pass1234");
        ServerResponse response = server.registerParticipant(gson.toJson(participantDtoRequest));
        assertEquals(200, response.getResponseCode());
        assertEquals("{}", response.getResponseData());

        RegisterParticipantDtoRequest participantDtoRequest1 = new RegisterParticipantDtoRequest("Alex", "Thompson",
                "Quarter To", "ThAlexandr", "Pass1234");
        response = server.registerParticipant(gson.toJson(participantDtoRequest));
        assertEquals(400, response.getResponseCode());
        System.out.println(response.getResponseData());
    }


}
