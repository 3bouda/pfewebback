package com.rh.backend.repo;

import com.rh.backend.model.Employee;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployerRepo extends  MongoRepository<Employee,String> {

    Optional<Employee> findById(String id);
    Optional <Employee> findByCin (String cin);
    
}