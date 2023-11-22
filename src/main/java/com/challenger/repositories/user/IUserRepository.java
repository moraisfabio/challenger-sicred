package com.challenger.repositories.user;

import com.challenger.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserRepository extends JpaRepository<User, String>{
    UserDetails findUserByUsername(String username);
}
