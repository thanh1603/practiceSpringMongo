package com.example.practiceSpring.reponsitory;

import com.example.practiceSpring.domain.dto.UserDto;
import com.example.practiceSpring.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReponsitory extends MongoRepository<User, String> {

//    @Query(value = "{id: ?0")
//    Optional<UserDto> findUserById(String id);
    Optional<User> findByEmail(String email);
}
