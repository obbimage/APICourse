package com.app.course.mapper.user;


import com.app.course.DTO.User.UserUpdateRequest;
import com.app.course.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserUpdateRequestMapper {
    UserUpdateRequestMapper INSTANCE = Mappers.getMapper(UserUpdateRequestMapper.class);

    User parseUser(UserUpdateRequest request);
    UserUpdateRequest parseUserUpdateRequest (User user);

    void updateUser(@MappingTarget User user,UserUpdateRequest request);
}
