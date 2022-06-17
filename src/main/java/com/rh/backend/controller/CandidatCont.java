package com.rh.backend.controller;

import java.util.List;
import java.util.Optional;

import com.rh.backend.model.Candidat;
import com.rh.backend.repo.CandidatRepo;

import org.springframework.http.HttpStatus;

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

@CrossOrigin
@RestController
@RequestMapping("/candidat")

public class CandidatCont {
    @Autowired
    private CandidatRepo candidatRepo;

    @GetMapping("")
    List<Candidat> index(){
        return candidatRepo.findAll();
    }

    @GetMapping("/{id}")
    Optional<Candidat> finfById(@PathVariable String id){
        return candidatRepo.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Candidat creat(@RequestBody Candidat candidat){
        return candidatRepo.save(candidat);
    }
    
    @PutMapping("/{id}")
    Candidat update(@PathVariable String id,@RequestBody Candidat candidat){
        Candidat  candidatFromDB = candidatRepo.findById(id).orElseThrow(RuntimeException::new);
        candidatFromDB.setNom(candidat.getNom());
        candidatFromDB.setPrenom(candidat.getPrenom());
        candidatFromDB.setTel(candidat.getTel());
        candidatFromDB.setEmail(candidat.getEmail());
        candidatFromDB.setAdresse(candidat.getAdresse());
        candidatFromDB.setCV(candidat.getCV());

        return candidatRepo.save(candidatFromDB);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        Candidat candidatToDelete = candidatRepo.findById(id).orElseThrow(RuntimeException::new);
        candidatRepo.delete(candidatToDelete);
    }
}
