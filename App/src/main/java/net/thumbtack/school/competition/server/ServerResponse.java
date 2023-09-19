package net.thumbtack.school.competition.server;

import com.google.gson.Gson;
import lombok.Getter;
import net.thumbtack.school.competition.dto.response.DtoResponse;
import net.thumbtack.school.competition.dto.response.ErrorDtoResponse;
import net.thumbtack.school.competition.exception.ServiceException;

@Getter
public class ServerResponse {
    private int responseCode;
    private String responseData;

    public <T extends DtoResponse> ServerResponse(T dtoResponse){
        Gson gson = new Gson();
        this.responseCode = 200;
        this.responseData = gson.toJson(dtoResponse);
    }

    public ServerResponse(ServiceException e){
        Gson gson = new Gson();
        ErrorDtoResponse errorDtoResponse = new ErrorDtoResponse(e);
        this.responseCode = 400;
        this.responseData = gson.toJson(errorDtoResponse);
    }



}
