package com.rh.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rh.backend.model.Conge;

public  interface CongeRepo  extends  MongoRepository<Conge,String> {
    
}
