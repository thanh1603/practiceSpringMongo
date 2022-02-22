package com.example.practiceSpring.controller;

import com.example.practiceSpring.domain.dto.CompanyDto;
import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.reponsitory.CompanyReponsitory;
import com.example.practiceSpring.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyReponsitory companyReponsitory;

    @Autowired
    CompanyService companyService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addCompany(@RequestBody CompanyDto companyDto){
        return this.companyService.createTypeProduct(companyDto);
    }


}
