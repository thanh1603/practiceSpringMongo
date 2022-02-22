package com.example.practiceSpring.domain.dto;

import com.example.practiceSpring.domain.model.TypeProduct;
import lombok.Data;

@Data
public class TypeProductDto {
    private String id;
    private String name;
    private Double vat;

    public TypeProductDto(){}

    public TypeProductDto(TypeProduct typeProduct){
        super();
        if (typeProduct != null){
            this.id = typeProduct.getId();
            this.name = typeProduct.getName();
            this.vat = typeProduct.getVat();
        }
    }
}
