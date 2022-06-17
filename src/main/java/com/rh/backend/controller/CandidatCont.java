package com.rh.backend.controller;

import java.util.List;
import java.util.Optional;

import com.rh.backend.model.Candidat;
import com.rh.backend.repo.CandidatRepo;
import com.rh.backend.service.ImageService;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/candidat")

public class CandidatCont {
    @Autowired
    private CandidatRepo candidatRepo;

    
    @Autowired
    private ImageService imageService;

    private String cvUrl;


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
    public Candidat creat(@RequestPart(name = "cv")  MultipartFile[] cv,
                          @RequestPart(required = false) String nom,
                          @RequestPart(required = false) String prenom,
                          @RequestPart(required = false) String tel,
                          @RequestPart(required = false) String email,
                          @RequestPart(required = false) String adresse
                        ) {
                            for (MultipartFile c : cv) {
                                try {
                                    String fileName = imageService.save(c);
                                    this.cvUrl = imageService.getImageUrl(fileName);
                    
                                } catch (Exception e) {
                                    System.out.println("no");
                                }
                            }
                            Candidat candidat = new Candidat();
                            candidat.setNom(nom);
                            candidat.setPrenom(prenom);
                            candidat.setTel(tel);
                            candidat.setEmail(email);
                            candidat.setAdresse(adresse);
                            candidat.setCvUrl(cvUrl);
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
        candidatFromDB.setCvUrl(candidat.getCvUrl());

        return candidatRepo.save(candidatFromDB);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        Candidat candidatToDelete = candidatRepo.findById(id).orElseThrow(RuntimeException::new);
        candidatRepo.delete(candidatToDelete);
    }
}
