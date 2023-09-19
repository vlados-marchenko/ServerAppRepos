package net.thumbtack.school.competition.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class GrantApplication {

    private Participant participant;
    private String applicationName;
    private String applicationDescription;
    private List<String> listOfDirections;
    private int sumOfGrant;
    private int id;

    public GrantApplication(String applicationName, String applicationDescription, List<String> listOfDirections, int sumOfGrant, int id){
        this.applicationName = applicationName;
        this.applicationDescription = applicationDescription;
        this.listOfDirections = listOfDirections;
        this.sumOfGrant = sumOfGrant;
        this.id = id;
    }

}
