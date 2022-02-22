package com.example.practiceSpring.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String passWord;
}
