package com.example.practiceSpring.service.impl;

import com.example.practiceSpring.common.Constant;
import com.example.practiceSpring.domain.dto.UserDto;
import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.domain.model.User;
import com.example.practiceSpring.reponsitory.UserReponsitory;
import com.example.practiceSpring.security.UserDetailsImpl;
import com.example.practiceSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserReponsitory userReponsitory;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ResponseObject> createUser(UserDto dto) {
        if (dto != null) {
            Optional<User> findUsers = userReponsitory.findByEmail(dto.getEmail());
            if (findUsers.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("failed", "Email already taken", "")
                );
            } else {
                User user = new User();
                user.setId(dto.getId());
                user.setName(dto.getName());
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
//            user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
                user.setFullName(dto.getFullName());
                user.setEmail(dto.getEmail());
                user.setAddress(dto.getAddress());
                user.setReview(dto.getReview());
                user.setRole(Constant.ROLE.user);
                user.setMoney(Constant.USER.money);
                user.setCompanyId(dto.getCompanyId());
                userReponsitory.save(user);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Insert success", user.getEmail())
                );

            }

        }
        return null;
    }


    @Override
    public Optional<User> findUserById(String id) {
        Optional<User> user = userReponsitory.findById(id);
        if (user.isPresent()) {
            return user;
        }

        return null;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<User> user = userReponsitory.findById(email);
        if (user.isPresent()) {
            return user;
        }

        return null;
    }

    @Override
    public ResponseEntity<ResponseObject> updateUser(UserDto dto, String id) {
        if (dto != null && id != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> userResult = userReponsitory.findById(userDetails.getId());
            Optional<String> opt = Optional.ofNullable(userResult).map(user -> user.get().getRole());
            if (opt.isPresent()) {
                String role = opt.get();
                if (Constant.ROLE.admin.equalsIgnoreCase(role)) {
                    Optional<User> user = userReponsitory.findById(id);
                    User update = user.get();
//                    update.setName(dto.getName());
//                    update.setAddress(dto.getAddress());
//                    update.setEmail(dto.getEmail());
//                    update.setCompanyId(dto.getCompanyId());
//                    update.setFullName(dto.getFullName());
//                    update.setMoney(dto.getMoney());
                    update.setRole(dto.getRole());
//                    update.setReview(dto.getReview());
                    userReponsitory.save(update);
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("ok", "Update success", update)
                    );

                } else {
                    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                            new ResponseObject("failed", "you do not have access", ""));
                }
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "data not found", ""));

        }
        return null;

    }

}



