package com.tka.sams.api.mapper;

import com.tka.sams.api.dto.UserResponse;
import com.tka.sams.api.entity.User;

public class UserMapper {

    public static UserResponse toResponse(User user) {

        if (user == null) {
            return null;
        }

        return new UserResponse(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }
}