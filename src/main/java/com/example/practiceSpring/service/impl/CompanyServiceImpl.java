package com.example.practiceSpring.service.impl;

import com.example.practiceSpring.domain.dto.CompanyDto;
import com.example.practiceSpring.domain.dto.TypeProductDto;
import com.example.practiceSpring.domain.model.Company;
import com.example.practiceSpring.domain.model.ResponseObject;
import com.example.practiceSpring.reponsitory.CompanyReponsitory;
import com.example.practiceSpring.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyReponsitory companyReponsitory;

    @Override
    public ResponseEntity<ResponseObject> createTypeProduct(CompanyDto companyDto) {
        if (companyDto != null) {
            boolean check = false;
            List<Company> listCompany = companyReponsitory.findAll();
            for (Company company: listCompany) {
                if (company.getName().equals(companyDto.getName()))
                    check = true;
                    break;

            }
            if (check){
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "already have a company","")
                );
            }else {
                Company comp = new Company();
                comp.setName(companyDto.getName());
                comp.setAddress(companyDto.getAddress());
                companyReponsitory.save(comp);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "add company success",comp.getName())
                );

            }
        }


        return null;
    }
}
