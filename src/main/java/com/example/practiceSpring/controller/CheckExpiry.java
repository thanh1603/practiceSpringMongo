package com.example.practiceSpring.controller;


import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckExpiry {

    @Autowired
    ProductService productService;

    @PostMapping("/checkProduct")
    public ResponseEntity<ResponseObject> checkExpiryProduct(@RequestBody String expiry) {
        return this.productService.checkExpiryProduct(expiry);
    }
}
