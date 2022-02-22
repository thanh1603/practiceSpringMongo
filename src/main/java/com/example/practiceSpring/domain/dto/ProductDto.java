package com.example.practiceSpring.domain.dto;

import com.example.practiceSpring.domain.model.Product;
import lombok.Data;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String status;
    private String typeProductId;
    private String userId;
    private Double price;
    private Double discount;
    private String expiry;
    private Double amount;

    public ProductDto(){}

    public ProductDto(Product product){
        super();
        if (product != null) {
            this.id = product.getId();
            this.name = product.getName();
            this.status = product.getStatus();
            this.typeProductId = product.getUserId();
            this.price = product.getPrice();
            this.discount = product.getDiscount();
            this.expiry = product.getExpiry();
            this.amount = product.getAmount();
        }
    }

}
