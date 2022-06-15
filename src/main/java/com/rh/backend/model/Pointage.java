package com.rh.backend.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Document
@Data
public class Pointage {
    @Id
    private String id;
    private String GPS;
    private String imagePath;
    
}
