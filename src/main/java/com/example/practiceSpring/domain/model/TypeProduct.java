package com.example.practiceSpring.domain.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "type_product")
public class TypeProduct {

    @Id
    private String id;
    private String name;
    private Double vat;

}
