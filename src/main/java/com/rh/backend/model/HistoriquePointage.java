package com.rh.backend.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Document
@Data
public class HistoriquePointage {
    @Id
    private String id;
    private String temps;
    private String abscent;
    private String nmbParJours;
    private String date;
}
