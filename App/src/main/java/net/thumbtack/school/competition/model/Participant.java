package net.thumbtack.school.competition.model;

// дай мне все application участника или  эксперта номер...
// и для application тоже, но свои номера
// дай мне информацию об application номер...

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Participant extends User{
    private String companyName;

    public Participant(String firstName, String lastName, String login, String password, int id, String companyName) {
        super(firstName, lastName, login, password, id);
        this.companyName = companyName;
    }

}
