package net.thumbtack.school.competition.dto.request.user;

public class LogoutDtoRequest {
    private String token;
    public LogoutDtoRequest(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }
}
