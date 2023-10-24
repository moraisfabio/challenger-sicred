package com.challenger.managevotes.domain.dtos.user;

import com.challenger.managevotes.domain.entities.user.UserRole;

public record CreateUserDTO (String username, String password, UserRole role){
}
