package com.example.practiceSpring.domain.dto;

import com.example.practiceSpring.domain.model.Company;
import lombok.Data;

@Data
public class CompanyDto {
    private String id;
    private String name;
    private String address;

    public CompanyDto(){}

    public CompanyDto(Company company){
        super();
        if (company != null){
            this.id = company.getId();
            this.name = company.getName();
            this.address = company.getAddress();
        }
    }
}
