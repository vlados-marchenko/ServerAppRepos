package net.thumbtack.school.competition.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private int id;

    public User(String firstName, String lastName, String login, String password, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.id = id;
    }


}
