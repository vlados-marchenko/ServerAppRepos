package net.thumbtack.school.competition.mapper;

import net.thumbtack.school.competition.dto.request.participant.RegisterParticipantDtoRequest;
import net.thumbtack.school.competition.model.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParticipantMapper {

    ParticipantMapper INSTANSE = Mappers.getMapper(ParticipantMapper.class);

    Participant participantDtoForParticipant(RegisterParticipantDtoRequest dtoRequest);
}
