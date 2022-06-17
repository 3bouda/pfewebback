package com.rh.backend.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rh.backend.model.Candidat;

public  interface CandidatRepo extends  MongoRepository<Candidat,String> {
    public Optional<Candidat> findById(String id);

}
