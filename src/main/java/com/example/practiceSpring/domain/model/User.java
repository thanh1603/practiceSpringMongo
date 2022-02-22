package com.example.practiceSpring.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String fullName;
    private String name;
    private String email;
    private String password;
    private String review;
    private String  role;
    private String address;
    private String companyId;
    private Double money;
}
