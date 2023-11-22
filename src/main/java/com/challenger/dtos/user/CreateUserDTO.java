package com.challenger.dtos.user;

import com.challenger.model.user.UserRole;

public record CreateUserDTO (String username, String password, UserRole role){
}
