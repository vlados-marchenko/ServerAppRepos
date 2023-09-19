package net.thumbtack.school.competition.mapper;

 import net.thumbtack.school.competition.dto.request.application.ApplicationDtoRequest;
 import net.thumbtack.school.competition.model.GrantApplication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GrantApplicationMapper {

    GrantApplicationMapper INSTANCE = Mappers.getMapper(GrantApplicationMapper.class);

    GrantApplication applicationDtoForApplication(ApplicationDtoRequest applicationDtoRequest);
}
