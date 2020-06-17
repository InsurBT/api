package com.example.apipsia.model.CaisseEtrangere;

import java.io.Serializable;

public class CaisseEtrangereCrud  implements Serializable{
    private int code;
    private String nom;
    private String  adresse;
    private int id;
    private int idpays;
    private String telephone ;
    private String fax;
    private String email;

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

    public int getIdpays() {
        return idpays;
    }

    public void setIdpays(int idpays) {
        this.idpays = idpays;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CaisseEtrangereCrud(int code, String nom, String adresse, int id, int idpays, String telephone, String fax,
            String email) {
        this.code = code;
        this.nom = nom;
        this.adresse = adresse;
        this.id = id;
        this.idpays = idpays;
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
    }

    public CaisseEtrangereCrud() {
    }

    

    
}
