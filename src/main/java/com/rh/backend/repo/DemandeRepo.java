package com.rh.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rh.backend.model.Demande;

public interface DemandeRepo extends MongoRepository<Demande,String>{
    
}
