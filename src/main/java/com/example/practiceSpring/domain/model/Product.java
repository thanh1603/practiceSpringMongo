package com.example.practiceSpring.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "product")
public class Product {

    @Id
    private String id;
    private String name;
    private String status;
    private String typeProductId;
    private String userId;
    private Double price;
    private Double discount;
    private String expiry;
    private Double amount;


}
