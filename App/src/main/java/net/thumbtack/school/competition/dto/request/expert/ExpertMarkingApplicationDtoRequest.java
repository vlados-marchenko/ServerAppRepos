package net.thumbtack.school.competition.dto.request.expert;


import lombok.AllArgsConstructor;
import lombok.Getter;
import net.thumbtack.school.competition.dto.request.application.ApplicationDtoRequest;

@Getter
@AllArgsConstructor
public class ExpertMarkingApplicationDtoRequest {
    private ApplicationDtoRequest applicationDtoRequest;
    private int mark;
}
