package com.rh.backend.controller;

import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rh.backend.repo.CongeRepo;
import com.rh.backend.model.Conge;

@CrossOrigin
@RestController
@RequestMapping("/conge")

public class CongeCont {
    @Autowired
    private CongeRepo congeRepo;
    @GetMapping("")
    List<Conge> index(){
        return congeRepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Conge creat(@RequestBody Conge conge){
        return congeRepo.save(conge);
    }
    
    @PutMapping("/{id}")
    Conge update(@PathVariable String id,@RequestBody Conge conge){
        Conge  congeFromDB = congeRepo.findById(id).orElseThrow(RuntimeException::new);
        congeFromDB.setDescription(conge.getDescription());
        congeFromDB.setDateDebut(conge.getDateDebut());
        congeFromDB.setDateFin(conge.getDateFin());
        congeFromDB.setType(conge.getType());
        congeFromDB.setPreuve(conge.getPreuve());
        congeFromDB.setEtat(conge.getEtat());

        return congeRepo.save(congeFromDB);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        Conge congeToDelete = congeRepo.findById(id).orElseThrow(RuntimeException::new);
        congeRepo.delete(congeToDelete);
    }
}
