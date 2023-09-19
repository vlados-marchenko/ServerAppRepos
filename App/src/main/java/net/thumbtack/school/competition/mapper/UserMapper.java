package net.thumbtack.school.competition.mapper;


import net.thumbtack.school.competition.dto.request.user.LoginDtoRequest;
import net.thumbtack.school.competition.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANSE = Mappers.getMapper(UserMapper.class);

    User userDtoForUser(LoginDtoRequest loginDtoRequest);
}
