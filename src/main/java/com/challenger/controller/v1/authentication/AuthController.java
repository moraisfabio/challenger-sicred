package com.challenger.controller.v1.authentication;

import com.challenger.dtos.authentication.AuthenticationDTO;
import com.challenger.dtos.authentication.TokenResponseDTO;
import com.challenger.dtos.user.CreateUserDTO;
import com.challenger.model.user.User;
import com.challenger.utils.security.TokenService;
import com.challenger.repositories.user.IUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor

@RestController
@RequestMapping("v1/auth")

public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final IUserRepository repository;
    @Autowired
    TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        logger.info("User Logged in!");
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody @Valid CreateUserDTO data){
        if (this.repository.findUserByUsername(data.username()) != null)  {
            logger.info("User already exists!");
            ResponseEntity.badRequest().build();
        };

        String encryptedPassword = new BCryptPasswordEncoder().encode((data.password()));
        User newUser = new User(data.username(), encryptedPassword, data.role());

        this.repository.save(newUser);
        logger.info("User registered with successful!");
        return ResponseEntity.ok().build();
    }
}