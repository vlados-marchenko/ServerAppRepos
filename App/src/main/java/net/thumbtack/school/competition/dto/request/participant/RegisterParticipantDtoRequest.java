package net.thumbtack.school.competition.dto.request.participant;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterParticipantDtoRequest {
    private String firstName;
    private String lastName;
    private String companyName;
    private String login;
    private String password;

    public RegisterParticipantDtoRequest(String firstName, String lastName, String companyName, String login, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.login = login;
        this.password = password;
    }


}
