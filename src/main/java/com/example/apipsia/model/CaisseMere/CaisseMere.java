package com.example.apipsia.model.CaisseMere;

import java.io.Serializable;

public class CaisseMere implements Serializable{
    private int code;
    private String nom;
    private String  adresse;
    private String pays;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
 

    public CaisseMere(int code, String nom, String adresse, String pays, int id) {
        this.code = code;
        this.nom = nom;
        this.adresse = adresse;
        this.pays = pays;
        this.id = id;
    }

    public CaisseMere() {
    }
    public String  getparam(){
        return "("+ this.code + ",'"+this.nom +"' , '"+ this.id +"')" ;

    }  
}