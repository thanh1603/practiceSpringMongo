package com.example.practiceSpring.domain.dto;

import com.example.practiceSpring.domain.model.User;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String fullName;
    private String name;
    private String email;
    private String review;
    private String  role;
    private String password;
    private String address;
    private String companyId;
    private Double money;
    public UserDto(){
    }

    public UserDto(User user) {
        super();
        if (user != null) {
            this.id = user.getId();
            this.fullName = user.getFullName();
            this.name = user.getName();
            this.email = user.getEmail();
            this.review = user.getReview();
            this.role = user.getRole();
            this.password = user.getPassword();
            this.address = user.getAddress();
            this.companyId = user.getCompanyId();
            this.money = user.getMoney();
        }
    }



}
