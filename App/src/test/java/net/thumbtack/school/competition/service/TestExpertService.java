package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dto.request.application.ApplicationDtoRequest;
import net.thumbtack.school.competition.dto.request.expert.ExpertMarkingApplicationDtoRequest;
import net.thumbtack.school.competition.dto.request.expert.RegisterExpertDtoRequest;
import net.thumbtack.school.competition.dto.request.user.LoginDtoRequest;
import net.thumbtack.school.competition.dto.response.user.LoginDtoResponse;
import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.server.Server;
import net.thumbtack.school.competition.server.ServerResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.*;

public class TestExpertService {
    private final Gson gson = new Gson();
    private final Server server = new Server();

    @Test
    public void testRegisterExpert() throws ServiceException {
        RegisterExpertDtoRequest expertDtoRequest = new RegisterExpertDtoRequest("firstE", "lastE",
                List.of("list", "grjkgnegk"), "expert", "120");
        ServerResponse response = server.registerExpert(gson.toJson(expertDtoRequest));
        assertEquals(200, response.getResponseCode());
        assertEquals("{}", response.getResponseData());

        RegisterExpertDtoRequest expertDtoRequest1 = new RegisterExpertDtoRequest("fE", "lE",
                List.of("list", "grjkgnegk"), "expert", "120344");
        ServerResponse res = server.registerExpert(gson.toJson(expertDtoRequest1));
        assertEquals(400, res.getResponseCode());
        System.out.println(res.getResponseData());
    }

    @Test
    public void testRegisterCopyExpert() throws ServiceException {
        RegisterExpertDtoRequest expertRequest1 = new RegisterExpertDtoRequest("firstName", "lastName",
                List.of("list1", "list2", "list3"), "expert2023", "qwerty2023");
        ServerResponse response = server.registerExpert(gson.toJson(expertRequest1));
        assertEquals(200, response.getResponseCode());
        assertEquals("{}", response.getResponseData());

        RegisterExpertDtoRequest expertRequest2 = new RegisterExpertDtoRequest("firstName", "lastName",
                List.of("list1", "list2", "list3"), "expert2023", "qwerty2023");
        response = server.registerExpert(gson.toJson(expertRequest2));
        assertEquals(400, response.getResponseCode());
        System.out.println(response.getResponseData());
    }

    @Test
    public void testExpertMarkingApplication() throws ServiceException {
        RegisterExpertDtoRequest expertRequest1 = new RegisterExpertDtoRequest("firstName", "lastName",
                List.of("list1", "list2", "list3"), "expert2023", "qwerty2023");
        ServerResponse response = server.registerExpert(gson.toJson(expertRequest1));

        LoginDtoRequest loginDtoRequest1 = new LoginDtoRequest("expert2023", "qwerty2023");
        ServerResponse res1 = server.loginUser(gson.toJson(loginDtoRequest1));
        String tokenForExpert = gson.fromJson(res1.getResponseData(), LoginDtoResponse.class).getToken();

        ApplicationDtoRequest applicationDtoRequest = new ApplicationDtoRequest("Research of architectural monuments",
                "Research", List.of("History", "Art"), 50000);
        ExpertMarkingApplicationDtoRequest markingApplicationDtoRequest = new ExpertMarkingApplicationDtoRequest(applicationDtoRequest, 4);
        ServerResponse markedApplication = server.markingApplicationByExpert(tokenForExpert, gson.toJson(markingApplicationDtoRequest));
        assertEquals(200, markedApplication.getResponseCode());


        ApplicationDtoRequest applicationDtoRequest1 = new ApplicationDtoRequest("Some researches","Research", List.of("History", "Art", "Politic"), 70000);
        ExpertMarkingApplicationDtoRequest markingApplicationDtoRequest1 = new ExpertMarkingApplicationDtoRequest(applicationDtoRequest1, 10);
        ExpertMarkingApplicationDtoRequest markingApplicationDtoRequest2 = new ExpertMarkingApplicationDtoRequest(applicationDtoRequest1, 0);

        ServerResponse badMarkResp1 = server.markingApplicationByExpert(tokenForExpert, gson.toJson(markingApplicationDtoRequest1));
        ServerResponse badMarkResp2 = server.markingApplicationByExpert(tokenForExpert, gson.toJson(markingApplicationDtoRequest2));

        assertEquals(400, badMarkResp1.getResponseCode());
        assertEquals(400, badMarkResp2.getResponseCode());

        ServerResponse badToken = server.markingApplicationByExpert("token", gson.toJson(markingApplicationDtoRequest));
        assertEquals(400, badToken.getResponseCode());
    }

}
