package com.example.practiceSpring.reponsitory;

import com.example.practiceSpring.domain.dto.TypeProductDto;
import com.example.practiceSpring.domain.model.TypeProduct;
import com.example.practiceSpring.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeProductReponsitory extends MongoRepository<TypeProduct, String> {

    Optional<TypeProduct> findByName(String name);


}
