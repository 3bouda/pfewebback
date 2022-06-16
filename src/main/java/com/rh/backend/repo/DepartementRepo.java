package com.rh.backend.repo;

import com.rh.backend.model.Departement;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartementRepo  extends  MongoRepository<Departement,String> {

    public Optional<Departement> findById(String id);
    
}
