package com.example.practiceSpring.controller;


import com.example.practiceSpring.domain.dto.UserDto;
import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.domain.model.User;
import com.example.practiceSpring.reponsitory.UserReponsitory;
import com.example.practiceSpring.request.LoginRequest;
import com.example.practiceSpring.response.JwtResponse;
import com.example.practiceSpring.security.JwtUtils;
import com.example.practiceSpring.security.UserDetailsImpl;
import com.example.practiceSpring.service.UserService;
import com.example.practiceSpring.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserReponsitory userReponsitory;

    @Autowired
    UserService userService;



    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createUser(@RequestBody UserDto dto){
//        userReponsitory.insert(user);
//            return "create success : " + user.getId();
//        userReponsitory.insert(user);
//         this.userServiceiml.createUser(dto);
        return this.userService.createUser(dto);
    }


    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable String id) {
            return this.userService.findUserById(id);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody UserDto dto, @PathVariable String id) {
        return this.userService.updateUser(dto, id);
    }



    @GetMapping("/detail")
    public ResponseEntity<User> getUserDetail(@RequestParam(value = "userId") String userId) {
        Optional<User> user = userService.findUserById(userId);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.isPresent()) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return new ResponseEntity(user, HttpStatus.BAD_REQUEST);
    }
}
