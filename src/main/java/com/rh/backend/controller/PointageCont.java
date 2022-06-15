package com.rh.backend.controller;
import java.util.List;

import com.rh.backend.model.Pointage;
import com.rh.backend.repo.PointageRepo;

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
@RequestMapping("/pointage")

public class PointageCont {
    @Autowired
    private PointageRepo pointageRepo;

    @GetMapping("")
    List<Pointage> index(){
        return pointageRepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Pointage creat(@RequestBody Pointage pointage){
        return pointageRepo.save(pointage);
    }
    
    @PutMapping("/{id}")
    Pointage update(@PathVariable String id,@RequestBody Pointage pointage){
        Pointage  pointageFromDB = pointageRepo.findById(id).orElseThrow(RuntimeException::new);
        pointageFromDB.setGPS(pointage.getGPS());
        pointageFromDB.setImagePath(pointage.getImagePath());

        return pointageRepo.save(pointageFromDB);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        Pointage pointageToDelete = pointageRepo.findById(id).orElseThrow(RuntimeException::new);
        pointageRepo.delete(pointageToDelete);
    }
}
//name:                                                                           b6ed7fef-ee79-4270-a237-d4c0ac8232eaPNG
//https://firebasestorage.googleapis.com/v0/b/facerecognition-b983d.appspot.com/o/0cf88e4d-9143-495b-ae1f-af0da61e3642PNG?alt=media
 //https://firebasestorage.googleapis.com/v0/b/facerecognition-b983d.appspot.com/o/0cf88e4d-9143-495b-ae1f-af0da61e3642PNG?alt=media&token=ff8538ba-2611-4b87-b53b-df20c1ef8b6e
 //https://firebasestorage.googleapis.com/v0/b/facerecognition-b983d.appspot.com/o/77fde773-569e-452c-82d2-ca6c9e85b3b4png?alt=media&token=25380e64-2eed-4759-91dc-78cececb4b24