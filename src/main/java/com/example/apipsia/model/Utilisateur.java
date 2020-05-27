package com.example.apipsia.model;

import java.io.Serializable;

public class Utilisateur implements Serializable {
    private long cod;
    private String nom;
    private String nomComplet;
    private int code_agence;
    private String agence;

    public Utilisateur(long cod, String nom, String nomComplet) {
        this.cod = cod;
        this.nom = nom;
        this.nomComplet = nomComplet;
    }

    public Utilisateur() {
        super();
    }


    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public int getCode_agence() {
        return code_agence;
    }

    public void setCode_agence(int code_agence) {
        this.code_agence = code_agence;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }
    
}