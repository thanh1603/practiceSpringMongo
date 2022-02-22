package com.example.practiceSpring.service;

import com.example.practiceSpring.domain.dto.ProductDto;
import com.example.practiceSpring.domain.dto.UserDto;
import com.example.practiceSpring.domain.model.Product;
import com.example.practiceSpring.domain.model.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<ResponseObject> updateProduct(ProductDto dto, String id);

    ResponseEntity<ResponseObject> addProduct(ProductDto dto);

    List<Product> findProductByPrice(Double price);

    List<Product> filterProduct(Double price, String name, String expiry, String status);

    ResponseEntity<ResponseObject> checkExpiryProduct(String expiry);
}
