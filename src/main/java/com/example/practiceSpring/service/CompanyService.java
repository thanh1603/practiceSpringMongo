package com.example.practiceSpring.service;

import com.example.practiceSpring.domain.dto.CompanyDto;
import com.example.practiceSpring.domain.dto.TypeProductDto;
import com.example.practiceSpring.domain.model.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface CompanyService {
    ResponseEntity<ResponseObject> createTypeProduct(CompanyDto companyDto);
}
