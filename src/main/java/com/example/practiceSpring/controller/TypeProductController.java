package com.example.practiceSpring.controller;


import com.example.practiceSpring.domain.dto.TypeProductDto;
import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.reponsitory.TypeProductReponsitory;
import com.example.practiceSpring.service.TypeProductService;
import com.example.practiceSpring.service.impl.TypeProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type_products")
public class TypeProductController {

    @Autowired
    TypeProductReponsitory typeProductReponsitory;

    @Autowired
    TypeProductService typeProductService;

//    @Autowired
//    TypeProductServiceImpl typeProductServiceImpl;


    @PostMapping("/add")
    public ResponseEntity<ResponseObject> createTypeProduct(@RequestBody TypeProductDto typeProductDto){
        return this.typeProductService.createTypeProduct(typeProductDto);
    }






}
