package com.rh.backend.controller;
import java.util.List;

import com.rh.backend.model.HistoriquePointage;
import com.rh.backend.repo.HistoriquePointageRepo;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/HistoriquePointage")
public class HistoriquePointageCont {
    @Autowired
    private HistoriquePointageRepo historiquePointageRepo;

    @GetMapping("")
    List<HistoriquePointage> index(){
        return historiquePointageRepo.findAll();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    HistoriquePointage creat(@RequestBody HistoriquePointage historiquePointage){
        return historiquePointageRepo.save(historiquePointage);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        HistoriquePointage HistoriquePointageToDelete = historiquePointageRepo.findById(id).orElseThrow(RuntimeException::new);
        historiquePointageRepo.delete(HistoriquePointageToDelete);
    }
}
