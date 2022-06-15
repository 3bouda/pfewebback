package com.rh.backend.repo;

import com.rh.backend.model.Event;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepo extends  MongoRepository<Event,String> {
    
}
