package net.thumbtack.school.competition.dto.request.application;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class ApplicationDtoRequest {
    private String nameOfRequest;
    private String descriptionOfRequest;
    private List<String> directions;
    private int sumOfGrant;
}
