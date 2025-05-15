package com.travelify.travelify.mapper;

import com.travelify.travelify.dto.request.SignUpRequest;
import com.travelify.travelify.dto.request.UserUpdateRequest;
import com.travelify.travelify.dto.response.UserResponse;
import com.travelify.travelify.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(SignUpRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
