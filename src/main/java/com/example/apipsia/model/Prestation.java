package com.example.apipsia.model;

import java.io.Serializable;

public class Prestation implements Serializable {
    private long id;
    private String type;
    private int nbrActes;
    private double montantEngage;
    private double montantPaye;

    public Prestation() {
        super();
    }

    public Prestation(long id, String type, int nbrActes, double montantEngage, double montantPaye) {
		this.id = id;
		this.type = type;
		this.nbrActes = nbrActes;
		this.montantEngage = montantEngage;
		this.montantPaye = montantPaye;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbrActes() {
        return nbrActes;
    }

    public void setNbrActes(int nbrActes) {
        this.nbrActes = nbrActes;
    }

    public double getMontantEngage() {
        return montantEngage;
    }

    public void setMontantEngage(double montantEngage) {
        this.montantEngage = montantEngage;
    }

    public double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(double montantPaye) {
        this.montantPaye = montantPaye;
    }
}