package com.example.apipsia.model;

import java.io.Serializable;

public class Agence implements Serializable {
    private int code;
    private String label;

    public Agence() {
        super();
    }

    public Agence(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}