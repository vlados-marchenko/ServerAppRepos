package net.thumbtack.school.competition.dto.response.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.thumbtack.school.competition.dto.response.DtoResponse;

@Getter
@AllArgsConstructor
public class ApplicationDtoResponse extends DtoResponse {
    String response;
}
