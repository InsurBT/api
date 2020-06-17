package com.example.apipsia.model;

import java.io.Serializable;

public class Pays implements Serializable {
    private int id;
    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Pays(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Pays() {
    }
    
    
    
}