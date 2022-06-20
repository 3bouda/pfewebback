package com.rh.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rh.backend.exceptions.NotFoundException;
import com.rh.backend.model.User;
import com.rh.backend.payload.ApiRequest.LoginRequest;
import com.rh.backend.payload.ApiResponse.LoginResponse;
import com.rh.backend.repo.UserRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private static final String STATUS_CODE_200_MESSAGE = "User LoggedIn with success";
    private static final String STATUS_CODE_400_MESSAGE = "Bad Request";
    private static final String STATUS_CODE_404_MESSAGE = "User not found";
    private static final String STATUS_CODE_500_MESSAGE = "Oops! Something went wrong";

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = new User();
        user.setFullName(loginRequest.getFullName());   
        user.setPassword(loginRequest.getPassword());

        Optional<User> optionalUser = userRepository.findByFullName(user.getFullName());
        if ( optionalUser.isPresent())
            return ResponseEntity.ok(new LoginResponse(optionalUser.get().getId(), optionalUser.get().getFullName()));
        else throw new NotFoundException(STATUS_CODE_404_MESSAGE);
    }
}
