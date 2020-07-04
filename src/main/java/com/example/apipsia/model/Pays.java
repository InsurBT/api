package com.example.apipsia.model;

import java.io.Serializable;

public class Pays implements Serializable {
    private int id;
    private String label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Pays(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public Pays() {
    }
    
    
    
}