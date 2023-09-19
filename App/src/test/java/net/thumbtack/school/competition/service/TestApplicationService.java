package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dto.request.application.AddApplicationDtoRequest;
import net.thumbtack.school.competition.exception.ServiceException;
import net.thumbtack.school.competition.server.Server;
import net.thumbtack.school.competition.server.ServerResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestApplicationService {
    private final Gson gson = new Gson();
    private final Server server = new Server();


    @Test
    @Disabled
    public void testAddApplication() throws ServiceException {
        AddApplicationDtoRequest addApplicationDtoRequest = new AddApplicationDtoRequest("Dark Galaxy", " App Description",
                List.of("Math", "Biology"), 350000);
        String jsonRequest = gson.toJson(addApplicationDtoRequest);
        ServerResponse response = server.addApplication(jsonRequest);
        assertEquals(200, response.getResponseCode());
    }
}
