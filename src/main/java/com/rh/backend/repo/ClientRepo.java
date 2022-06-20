package com.rh.backend.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rh.backend.model.Clientt;


public interface ClientRepo extends  MongoRepository<Clientt,String> {
    public Optional<Clientt> findById(String id);

}