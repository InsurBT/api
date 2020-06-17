package com.example.apipsia.model.CaisseMere;

import java.io.Serializable;

public class CaisseMereCrud implements Serializable {
    private int code;
    private String nom;
    private String  adresse;
    private int id;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CaisseMereCrud(int code, String nom, String adresse, int id) {
        this.code = code;
        this.nom = nom;
        this.adresse = adresse;
        this.id = id;
    }

    public CaisseMereCrud() {
    }
    
    
    
 
}