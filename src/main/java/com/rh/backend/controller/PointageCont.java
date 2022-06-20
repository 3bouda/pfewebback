package com.rh.backend.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rh.backend.model.Employee;
import com.rh.backend.model.Pointage;
import com.rh.backend.repo.EmployerRepo;
import com.rh.backend.repo.PointageRepo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


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
    

//get image from postman and send it to flask
    @RequestMapping(value = "/checkface", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImages(@RequestPart("file") final MultipartFile[] files) throws IOException {
    LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
    String response;
    HttpStatus httpStatus = HttpStatus.CREATED;

    try {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                map.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        String url = "http://localhost:8885/image";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        response = restTemplate.postForObject(url, requestEntity, String.class);

    } catch (HttpStatusCodeException e) {
        httpStatus = HttpStatus.valueOf(e.getStatusCode().value());
        response = e.getResponseBodyAsString();
    } catch (Exception e) {
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        response = e.getMessage();
    }


    return new ResponseEntity<>(response, httpStatus);
}
    

//get info from flask
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/present")
    Employee addpoint(@RequestBody String id){
        String idEmployee = id.substring(1,id.length()-1 );
        Employee employeeFromDB = employeeRepo.findById(idEmployee).orElseThrow(RuntimeException::new);;
        //System.out.println(employeeFromDB);
        if(employeeFromDB.getPresent() == null){
            List<Integer> presence = new ArrayList<Integer>();
            presence.add(1);
            employeeFromDB.setPresent(presence);
        }
        else{
            List<Integer> presence = employeeFromDB.getPresent();
            presence.add(1);
            employeeFromDB.setPresent(presence);
        }
        // List<Integer> presence = employeeFromDB.getPresent();
        // presence.add(0);
        // System.out.println(presence);

        // employeeFromDB.setPresent(presence);
        
        return employeeRepo.save(employeeFromDB);
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
