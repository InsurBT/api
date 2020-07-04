package com.example.apipsia.model;

public class DirectionRegional {
    private int Code;
    private String Designation;
    private String Adresse;
    private String Ville;
    private int idville;

    public int getCode() {
        return Code;
    }

    public int getIdville() {
        return idville;
    }

    public void setIdville(int idville) {
        this.idville = idville;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    @Override
    public String toString() {
        return "DirectionRegional [Adresse=" + Adresse + ", Code=" + Code + ", Designation=" + Designation + ", Ville=" + Ville
                + "]";
    }

	public DirectionRegional(int code, String designation, String adresse, String ville) {
		Code = code;
		Designation = designation;
		Adresse = adresse;
		Ville = ville;
	}
    
    public DirectionRegional() {
        super();
    }
}