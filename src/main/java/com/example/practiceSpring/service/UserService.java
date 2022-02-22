package com.example.practiceSpring.service;

import com.example.practiceSpring.domain.dto.UserDto;
import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.domain.model.User;
import com.example.practiceSpring.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
      ResponseEntity<ResponseObject> createUser(UserDto dto);

     Optional<User> findUserById(String id);
     Optional<User> findUserByEmail(String email);

     ResponseEntity<ResponseObject> updateUser(UserDto dto, String id);

}
