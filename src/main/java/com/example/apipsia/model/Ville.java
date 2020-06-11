package com.example.apipsia.model;

import java.io.Serializable;

public class Ville  implements Serializable{
    private int id;
    private String nom;
    private int codpays;

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

    public Ville() {
    }


    public int getCodpays() {
        return codpays;
    }

    public void setCodpays(int codpays) {
        this.codpays = codpays;
    }

    public Ville(int id, String nom, int codpays) {
        this.id = id;
        this.nom = nom;
        this.codpays = codpays;
    }
  

    
}