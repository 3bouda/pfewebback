package com.rh.backend.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Document
@Data
public class Event {
    @Id
    private String id;
    private String datedebut;
    private String datefin;
    private String description;
    private String objet;
    private String type;
    public List<Employee> employees;
    public List<Employee> candidat;
}
