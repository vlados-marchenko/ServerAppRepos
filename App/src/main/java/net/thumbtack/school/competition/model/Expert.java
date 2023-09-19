package net.thumbtack.school.competition.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Expert extends User{
    private List<String> listExpertOfDirections;

    public Expert(String firstName, String lastName, String login, String password, int id, List<String> listExpertOfDirections) {
        super(firstName, lastName, login, password, id);
        this.listExpertOfDirections = listExpertOfDirections;
    }

}
