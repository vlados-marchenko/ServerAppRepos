package net.thumbtack.school.competition.mapper;

import net.thumbtack.school.competition.dto.request.expert.RegisterExpertDtoRequest;
import net.thumbtack.school.competition.model.Expert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpertMapper {

    ExpertMapper INSTANSE = Mappers.getMapper(ExpertMapper.class);

    Expert expertDtoForExpert(RegisterExpertDtoRequest dtoRequest);
}
