package com.example.practiceSpring.service;

import com.example.practiceSpring.common.ProductByRequest;
import com.example.practiceSpring.domain.dto.ProductDto;
import com.example.practiceSpring.domain.model.Product;
import com.example.practiceSpring.domain.model.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface ShoppingService {
    ResponseEntity<ResponseObject> shopping(ProductByRequest product);
}
