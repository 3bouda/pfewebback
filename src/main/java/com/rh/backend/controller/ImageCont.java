package com.rh.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.rh.backend.model.Employee;
import com.rh.backend.repo.EmployerRepo;
import com.rh.backend.service.ImageService;


@CrossOrigin
@RestController("*")
@RequestMapping("/image")
public class ImageCont {
    @Autowired
    ImageService imageService;

    @Autowired
    EmployerRepo emplyeeRepo;

    // @PostMapping
    // @ResponseBody
    // public Employee create(@RequestParam(name = "file") MultipartFile[] files,
    //                        @RequestParam String departementId,
    //                        @RequestParam String nom,
    //                        @RequestParam String prenom,
    //                        @RequestParam String tel,
    //                        @RequestParam String email,
    //                        @RequestParam String adresse,
    //                        @RequestParam String motDePasse,
    //                        @RequestParam String CIN,
    //                        @RequestParam String etat
    //                      ) {
    //         for (MultipartFile file : files) {
    //             try {
    //                 String fileName = imageService.save(file);
    //                 String imageUrl = imageService.getImageUrl(fileName);

    //                 Employee employee = new Employee();

    //                 employee.setDepartementId(departementId);
    //                 employee.setNom(nom);
    //                 employee.setPrenom(prenom);
    //                 employee.setTel(tel);
    //                 employee.setEmail(email);
    //                 employee.setAdresse(adresse);
    //                 employee.setMotDePasse(motDePasse);
    //                 employee.setCIN(CIN);
    //                 employee.setEtat(etat);
    //                 employee.setImageUrl(imageUrl);

    //                 return emplyeeRepo.save(employee);
    //             } catch (Exception e) {
    //             System.out.println("no");
    //             }
    //         }
    //         return null;
    // }


}