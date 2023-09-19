package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dto.request.expert.RegisterExpertDtoRequest;
import net.thumbtack.school.competition.dto.request.participant.RegisterParticipantDtoRequest;
import net.thumbtack.school.competition.dto.request.user.LoginDtoRequest;
import net.thumbtack.school.competition.dto.response.user.LoginDtoResponse;
import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.server.Server;
import net.thumbtack.school.competition.server.ServerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserService {
    private final Gson gson = new Gson();
    private final Server server = new Server();

    @BeforeEach
    public void testRegistration() throws ServiceException {
        RegisterExpertDtoRequest expertDtoRequest = new RegisterExpertDtoRequest("firstName", "lastName",
                List.of("list1", "list2", "list3"), "expert2023", "qwerty2023");
        server.registerExpert(gson.toJson(expertDtoRequest));
        RegisterParticipantDtoRequest participantDtoRequest = new RegisterParticipantDtoRequest("Alex", "Thompson",
                "Quarter To", "ThAlex", "Password1234");
        server.registerParticipant(gson.toJson(participantDtoRequest));
    }

    @Test
    public void testLoginMethod() throws ServiceException {
        LoginDtoRequest loginDtoRequest1 = new LoginDtoRequest("expert2023", "qwerty2023");
        ServerResponse res1 = server.loginUser(gson.toJson(loginDtoRequest1));

        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest("ThAlex", "Password1234");
        ServerResponse res2 = server.loginUser(gson.toJson(loginDtoRequest2));

        assertEquals(200, res1.getResponseCode());
        assertEquals(200, res2.getResponseCode());

        String tokenForExpert = gson.fromJson(res1.getResponseData(), LoginDtoResponse.class).getToken();
        String tokenForParticipant = gson.fromJson(res2.getResponseData(), LoginDtoResponse.class).getToken();
        assertNotEquals(tokenForExpert, tokenForParticipant);


        LoginDtoRequest loginRequest1 = new LoginDtoRequest("", "qwerty2023");
        ServerResponse response = server.loginUser(gson.toJson(loginRequest1));
        assertEquals(400, response.getResponseCode());

        LoginDtoRequest loginRequest2 = new LoginDtoRequest("expert2023", "");
        response = server.loginUser(gson.toJson(loginRequest2));
        assertEquals(400, response.getResponseCode());
    }


    @Test
    public void testLogoutMethod() throws ServiceException {
        LoginDtoRequest dtoRequest = new LoginDtoRequest("expert2023", "qwerty2023");
        String serverResponseEx = server.loginUser(gson.toJson(dtoRequest)).getResponseData();
        String tokenForExpert = gson.fromJson(serverResponseEx, LoginDtoResponse.class).getToken();

        ServerResponse expertToLogout = server.logoutUser(tokenForExpert);
        assertEquals(200, expertToLogout.getResponseCode());

        ServerResponse expertToLogoutAgain = server.logoutUser(tokenForExpert);
        assertEquals(400, expertToLogoutAgain.getResponseCode());
        System.out.println(expertToLogoutAgain.getResponseData());


        LoginDtoRequest dtoRequest1 = new LoginDtoRequest("ThAlex", "Password1234");
        String serverResponsePart = server.loginUser(gson.toJson(dtoRequest1)).getResponseData();
        String tokenForParticipant = gson.fromJson(serverResponsePart, LoginDtoResponse.class).getToken();

        ServerResponse participantToLogout = server.logoutUser(tokenForParticipant);
        assertEquals(200, participantToLogout.getResponseCode());

        ServerResponse participantToLogoutAgain = server.logoutUser(tokenForParticipant);
        assertEquals(400, participantToLogoutAgain.getResponseCode());
        System.out.println(participantToLogoutAgain.getResponseData());

    }



    @Test
    public void testLeavingUserFromServer() throws ServiceException {
        LoginDtoRequest dtoRequest = new LoginDtoRequest("expert2023", "qwerty2023");
        String serverResponseEx = server.loginUser(gson.toJson(dtoRequest)).getResponseData();
        String tokenForExpert = gson.fromJson(serverResponseEx, LoginDtoResponse.class).getToken();

        ServerResponse expertToLeave = server.leaveServer(tokenForExpert);
        assertEquals(200, expertToLeave.getResponseCode());

        ServerResponse expertToLeaveAgain = server.leaveServer(tokenForExpert);
        assertEquals(400, expertToLeaveAgain.getResponseCode());
        System.out.println(expertToLeaveAgain.getResponseData());


        LoginDtoRequest dtoRequest1 = new LoginDtoRequest("ThAlex", "Password1234");
        String serverResponsePart = server.loginUser(gson.toJson(dtoRequest1)).getResponseData();
        String tokenForParticipant = gson.fromJson(serverResponsePart, LoginDtoResponse.class).getToken();

        ServerResponse participantToLeave = server.leaveServer(tokenForParticipant);
        assertEquals(200, participantToLeave.getResponseCode());

        ServerResponse participantToLeaveAgain = server.leaveServer(tokenForParticipant);
        assertEquals(400, participantToLeaveAgain.getResponseCode());
        System.out.println(participantToLeaveAgain.getResponseData());
    }


}
