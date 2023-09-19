package net.thumbtack.school.competition.dto.request.application;


import java.util.List;

public class AddApplicationDtoRequest {
    private String applicationName;
    private String applicationDescription;
    private List<String> listOfDirections;
    private int sumOfGrant;


    public AddApplicationDtoRequest(String applicationName, String applicationDescription, List<String> listOfDirections, int sumOfGrant) {
        this.applicationName = applicationName;
        this.applicationDescription = applicationDescription;
        this.listOfDirections = listOfDirections;
        this.sumOfGrant = sumOfGrant;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public List<String> getListOfDirections() {
        return listOfDirections;
    }

    public int getSumOfGrant() {
        return sumOfGrant;
    }

}
