package com.rh.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Document
@Data
public class Conge {
    @Id
    private String id;
    private String idemploye;

    private String imageEmploye;
    private String nomEmploye;
    private String prenomEmploye;
    private String emailEmploye;

    private String description;
    private String dateDebut;
    private String dateFin;
    private String type;
    private String preuve; //imageURL
    private String etat;

}
