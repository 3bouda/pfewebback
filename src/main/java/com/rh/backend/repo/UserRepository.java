package com.rh.backend.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rh.backend.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional <User> findByFullName (String fullName);
}
