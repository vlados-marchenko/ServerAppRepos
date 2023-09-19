package net.thumbtack.school.competition.dto.response.user;

import lombok.Getter;
import net.thumbtack.school.competition.dto.response.DtoResponse;
@Getter
public class LoginDtoResponse extends DtoResponse {
    private String token;
    public LoginDtoResponse(String token){
        this.token = token;
    }

}
