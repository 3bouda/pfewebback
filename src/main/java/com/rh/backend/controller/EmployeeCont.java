package com.rh.backend.controller;

import java.util.List;

import com.rh.backend.model.Employee;
import com.rh.backend.repo.EmployerRepo;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeCont {

    @Autowired
    private EmployerRepo employeeRepo;

    @Autowired
    private ImageService imageService;

    @GetMapping("")
    List<Employee> index(){
        return employeeRepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Employee create(@RequestParam(name = "file") MultipartFile[] files,
                           @RequestParam String departementId,
                           @RequestParam String nom,
                           @RequestParam String prenom,
                           @RequestParam String tel,
                           @RequestParam String email,
                           @RequestParam String adresse,
                           @RequestParam String motDePasse,
                           @RequestParam String CIN,
                           @RequestParam String etat
                        ) {
        for (MultipartFile file : files) {
            try {
                String fileName = imageService.save(file);
                String imageUrl = imageService.getImageUrl(fileName);

                Employee employee = new Employee();

                employee.setDepartementId(departementId);
                employee.setNom(nom);
                employee.setPrenom(prenom);
                employee.setTel(tel);
                employee.setEmail(email);
                employee.setAdresse(adresse);
                employee.setMotDePasse(motDePasse);
                employee.setCIN(CIN);
                employee.setEtat(etat);
                employee.setImageUrl(imageUrl);
                return employeeRepo.save(employee);
            } catch (Exception e) {
                System.out.println("no");
            }
        }
        return null;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/video")
    public void create(@RequestParam(name = "file") MultipartFile[] files){
        for (MultipartFile file : files) {
            try {
                String fileName = imageService.save(file);
                String imageUrl = imageService.getImageUrl(fileName);
                System.out.println(imageUrl);
            } catch (Exception e) {
                System.out.println("no");
            }
        }   
    }   
    
    
    
    @PutMapping("/{id}")
    Employee update(@PathVariable String id,@RequestBody Employee employee){
        Employee  employeeFromDB = employeeRepo.findById(id).orElseThrow(RuntimeException::new);
        
        employeeFromDB.setDepartementId(employee.getDepartementId());
        employeeFromDB.setNom(employee.getNom());
        employeeFromDB.setPrenom(employee.getPrenom());
        employeeFromDB.setTel(employee.getTel());
        employeeFromDB.setEmail(employee.getEmail());
        employeeFromDB.setAdresse(employee.getAdresse());
        employeeFromDB.setMotDePasse(employee.getMotDePasse());
        employeeFromDB.setCIN(employee.getCIN());
        employeeFromDB.setEtat(employee.getEtat());

        return employeeRepo.save(employeeFromDB);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        Employee employeeToDelete = employeeRepo.findById(id).orElseThrow(RuntimeException::new);
        employeeRepo.delete(employeeToDelete);
    }
}