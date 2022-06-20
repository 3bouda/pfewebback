package com.rh.backend.controller;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        return congeRepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED) 
    @PostMapping("")
    Conge creat(@RequestBody Conge conge){
        Optional<Employee> employe = employeeRepo.findById(conge.getIdemploye());
        conge.setImageEmploye(employe.get().getImageUrl());
        conge.setNomEmploye(employe.get().getNom());
        conge.setPrenomEmploye(employe.get().getPrenom());
        conge.setPrenomEmploye(employe.get().getPrenom());
        conge.setEmailEmploye(employe.get().getEmail());
        return congeRepo.save(conge);
    }
    
    @GetMapping("accept/{id}")
    Conge accept(@PathVariable String id){
        Conge  congeFromDB = congeRepo.findById(id).orElseThrow(RuntimeException::new);
        congeFromDB.setEtat("accept");
        return congeRepo.save(congeFromDB);
    }

    @GetMapping("refus/{id}")
    Conge refus(@PathVariable String id){
        Conge  congeFromDB = congeRepo.findById(id).orElseThrow(RuntimeException::new);
        congeFromDB.setEtat("refus");
        return congeRepo.save(congeFromDB);
    }
}
