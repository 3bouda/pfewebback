package com.rh.backend.repo;

import com.rh.backend.model.Employee;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployerRepo extends  MongoRepository<Employee,String> {

    public Optional<Employee> findById(String id);
    
}