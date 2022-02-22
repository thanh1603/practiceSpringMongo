package com.example.practiceSpring.reponsitory;

import com.example.practiceSpring.domain.dto.CompanyDto;
import com.example.practiceSpring.domain.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyReponsitory extends MongoRepository<Company, String > {
}
