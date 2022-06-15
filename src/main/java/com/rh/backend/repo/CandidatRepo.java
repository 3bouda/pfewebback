package com.rh.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rh.backend.model.Candidat;

public  interface CandidatRepo extends  MongoRepository<Candidat,String> {
    
}
