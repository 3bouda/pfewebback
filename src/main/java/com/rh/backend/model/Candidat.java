package com.rh.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Candidat {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String adresse;

    private String cvUrl;
}
