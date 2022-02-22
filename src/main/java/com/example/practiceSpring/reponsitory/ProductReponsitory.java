package com.example.practiceSpring.reponsitory;

import com.example.practiceSpring.domain.dto.ProductDto;
import com.example.practiceSpring.domain.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReponsitory extends MongoRepository<Product, String> {
    @Query("{$and: ['price': {$eq: '?0'},'name': ?1, 'expiry': {$eq: '?2'}, 'status': ?3]}")
    List<Product> filterProduct(Double price, String name, Double expiry, String status);

}
