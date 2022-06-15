package com.rh.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Resizable {
    private boolean beforeStart;
    private boolean afterEnd ;
    public Resizable(final boolean beforeStart,
        final boolean afterEnd ){
        this.afterEnd = beforeStart;
        this.beforeStart =afterEnd ;
    }
}
