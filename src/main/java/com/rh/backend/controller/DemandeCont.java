package com.rh.backend.controller;
import com.rh.backend.model.Demande;
import com.rh.backend.model.Employee;
import com.rh.backend.repo.DemandeRepo;
import com.rh.backend.repo.EmployerRepo;

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

@CrossOrigin
@RestController
@RequestMapping("/demande")
public class DemandeCont {

    @Autowired
    private DemandeRepo demandeRepo;
    
    @Autowired
    private EmployerRepo employeeRepo;

    @GetMapping("")
    List<Demande> index(){
        List<Demande>demandes = demandeRepo.findAll();

        for(Demande demande : demandes){
            Optional<Employee> employe = employeeRepo.findById(demande.getIdemploye());
          
            demande.setImageEmploye(employe.get().getImageUrl());
            demande.setNomEmploye(employe.get().getNom());
            demande.setPrenomEmploye(employe.get().getPrenom());
            demande.setPrenomEmploye(employe.get().getPrenom());
            demande.setEmailEmploye(employe.get().getEmail());
            demandeRepo.save(demande);
        }
        return demandeRepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Demande creat(@RequestBody Demande demande){
        return demandeRepo.save(demande);
    }

    @PutMapping("/{id}")
    Demande update(@PathVariable String id,@RequestBody Demande demande){
        Demande  demandeFromDB = demandeRepo.findById(id).orElseThrow(RuntimeException::new);

        demandeFromDB.setObjet(demande.getObjet());
        demandeFromDB.setDescription(demande.getDescription());
        
        return demandeRepo.save(demandeFromDB);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        Demande demandeToDelete = demandeRepo.findById(id).orElseThrow(RuntimeException::new);
        demandeRepo.delete(demandeToDelete);
    }


}
