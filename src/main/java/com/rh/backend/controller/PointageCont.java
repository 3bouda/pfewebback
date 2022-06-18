package com.rh.backend.controller;
import java.util.List;
import java.util.Optional;

import com.rh.backend.model.Employee;
import com.rh.backend.model.Pointage;
import com.rh.backend.repo.EmployerRepo;
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

    @Autowired
    private EmployerRepo employeeRepo;

    @GetMapping("")
    List<Pointage> index(){
        return pointageRepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/present")
    Void addpoint(@RequestBody String id){
        System.out.println(id);
        String idEmployee = id.substring(1,id.length()-1 );
        System.out.println(idEmployee);

        Optional<Employee> employee = employeeRepo.findById(idEmployee);
        List<Integer> presence = employee.get().getPresent();
        presence.add(0);
        return null;
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
