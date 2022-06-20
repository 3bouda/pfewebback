package com.rh.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Document
@Data
public class Clientt {
    @Id
    private String id;
    private String nom;
    private String societe;
    private String logo;
    private String list;
}
