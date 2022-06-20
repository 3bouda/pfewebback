package com.rh.backend.controller;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

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
import com.rh.backend.repo.EmployerRepo;
import com.rh.backend.model.Conge;
import com.rh.backend.model.Employee;

@CrossOrigin
@RestController
@RequestMapping("/conge")

public class CongeCont {

    @Autowired
    private CongeRepo congeRepo;
    
    @Autowired
    private EmployerRepo employeeRepo;

    @GetMapping("")
    List<Conge> index(){
        List<Conge>conges = congeRepo.findAll();
        
        for(Conge conge : conges){
            Optional<Employee> employe = employeeRepo.findById(conge.getIdemploye());
            conge.setImageEmploye(employe.get().getImageUrl());
            conge.setNomEmploye(employe.get().getNom());
            conge.setPrenomEmploye(employe.get().getPrenom());
            conge.setPrenomEmploye(employe.get().getPrenom());
            conge.setEmailEmploye(employe.get().getEmail());
            congeRepo.save(conge);
        }
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
        congeFromDB.setIdemploye(conge.getIdemploye());
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
