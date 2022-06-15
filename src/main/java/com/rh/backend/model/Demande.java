package com.rh.backend.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Document
@Data
public class Demande {
    @Id
    private String id;
    private String objet;
    private String description;
}
