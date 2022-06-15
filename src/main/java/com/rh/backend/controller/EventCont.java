package com.rh.backend.controller;

import java.util.List;

import com.rh.backend.model.Event;
import com.rh.backend.repo.EventRepo;

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
@RequestMapping("/event")
public class EventCont {
    @Autowired
    private EventRepo eventRepo;

    @GetMapping("")
    List<Event> index(){
        return eventRepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Event creat(@RequestBody Event event){
        return eventRepo.save(event);
    }

    @PutMapping("/{id}")
    Event update(@PathVariable String id,@RequestBody Event event){
        Event  eventFromDB = eventRepo.findById(id).orElseThrow(RuntimeException::new);

        eventFromDB.setDatedebut(event.getDatedebut());
        eventFromDB.setDatefin(event.getDatefin());
        eventFromDB.setDescription(event.getDescription());
        eventFromDB.setObjet(event.getObjet());


        return eventRepo.save(eventFromDB);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        Event eventToDelete = eventRepo.findById(id).orElseThrow(RuntimeException::new);
        eventRepo.delete(eventToDelete);
    }

    
}