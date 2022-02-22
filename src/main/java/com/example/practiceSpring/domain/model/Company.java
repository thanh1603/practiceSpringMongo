package com.example.practiceSpring.domain.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "company")
public class Company {
    @Id
    private String id;
    private String name;
    private String address;


}
