package net.thumbtack.school.competition.dto.request.expert;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RegisterExpertDtoRequest {
    private String firstName;
    private String lastName;
    private List<String> listExpertOfDirections;
    private String login;
    private String password;

    public RegisterExpertDtoRequest(String firstName, String lastName, List<String> listExpertOfDirections, String login, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.listExpertOfDirections = listExpertOfDirections;
        this.login = login;
        this.password = password;
    }


}
