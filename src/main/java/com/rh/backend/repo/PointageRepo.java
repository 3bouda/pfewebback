package com.rh.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rh.backend.model.Pointage;

public interface PointageRepo  extends  MongoRepository<Pointage,String> {
    
}
