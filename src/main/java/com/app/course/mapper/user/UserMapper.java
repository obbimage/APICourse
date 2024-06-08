package com.app.course.mapper.user;


import com.app.course.DTO.User.UserDTO;
import com.app.course.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);
    User userDTOtoUser(UserDTO userDTO);
    void updateUser(@MappingTarget User user, UserDTO userDTO);

}
