package net.thumbtack.school.competition.dto.response.user;

import net.thumbtack.school.competition.dto.response.DtoResponse;

public class LogoutDtoResponse extends DtoResponse {
    private String token;
    public LogoutDtoResponse(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }
}
