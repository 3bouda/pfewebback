package com.rh.backend.repo;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rh.backend.model.HistoriquePointage;

public  interface HistoriquePointageRepo extends  MongoRepository<HistoriquePointage,String>{
    
}
