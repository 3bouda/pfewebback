package com.rh.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Departement {
    @Id
    private String id;
    private String nom;
    private String HorraireTravail;
    private String NbrJrSem;
    private String nbrJrCon;
    private String modePoin;
    private String NbrPoinJr;
    private String horrairePoin;
}
