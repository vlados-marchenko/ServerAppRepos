package net.thumbtack.school.competition.dto.response;

import lombok.Setter;
import net.thumbtack.school.competition.exception.ServiceException;

@Setter
public class ErrorDtoResponse {
    String error;
    public ErrorDtoResponse(ServiceException serviceException){
        setError(serviceException.getServerErrorCode().getErrorCode());
    }
}
