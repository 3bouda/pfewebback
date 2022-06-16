package com.rh.backend.controller;
import java.util.List;
import java.util.Optional;

import com.rh.backend.model.Departement;
import com.rh.backend.repo.DepartementRepo;
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
@RequestMapping("/departement")
public class DepartCont {
    @Autowired
    private DepartementRepo departementRepo;

    @GetMapping("")
    List<Departement> index(){
        return departementRepo.findAll();
    }

    @GetMapping("/{id}")
    Optional<Departement> finfById(@PathVariable String id){
        return departementRepo.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Departement creat(@RequestBody Departement departement){
        return departementRepo.save(departement);
    }

    @PutMapping("/{id}")
    Departement update(@PathVariable String id,@RequestBody Departement departement){
        Departement  departementFromDB = departementRepo.findById(id).orElseThrow(RuntimeException::new);

        departementFromDB.setNom(departement.getNom());
        departementFromDB.setHorrairePoin(departement.getHorrairePoin());
        departementFromDB.setHorraireTravail(departement.getHorraireTravail());
        departementFromDB.setNbrJrSem(departement.getNbrJrSem());
        departementFromDB.setNbrJrCon(departement.getNbrJrCon());
        departementFromDB.setModePoin(departement.getModePoin());
        departementFromDB.setNbrPoinJr(departement.getNbrPoinJr());

        return departementRepo.save(departementFromDB);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        Departement departementToDelete = departementRepo.findById(id).orElseThrow(RuntimeException::new);
        departementRepo.delete(departementToDelete);
    }
}
