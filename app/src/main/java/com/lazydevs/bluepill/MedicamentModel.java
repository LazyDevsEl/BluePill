package com.lazydevs.bluepill;

public class MedicamentModel {
    private int id;
    private String nom;
    private String laboratoire;
    private String presentation;
    private float c_initail;
    private float c_minimal;
    private float c_maximal;
    private float volumep;
    private float prix;

    public MedicamentModel(int id, String nom, String laboratoire, String presentation, float c_initail, float c_minimal, float c_maximal, float volumep, float prix) {
        this.id = id;
        this.nom = nom;
        this.laboratoire = laboratoire;
        this.presentation = presentation;
        this.c_initail = c_initail;
        this.c_minimal = c_minimal;
        this.c_maximal = c_maximal;
        this.volumep = volumep;
        this.prix = prix;
    }

    public MedicamentModel() {
    }

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

    public String getLaboratoire() {
        return laboratoire;
    }

    public void setLaboratoire(String laboratoire) {
        this.laboratoire = laboratoire;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public float getC_initail() {
        return c_initail;
    }

    public void setC_initail(float c_initail) {
        this.c_initail = c_initail;
    }

    public float getC_minimal() {
        return c_minimal;
    }

    public void setC_minimal(float c_minimal) {
        this.c_minimal = c_minimal;
    }

    public float getC_maximal() {
        return c_maximal;
    }

    public void setC_maximal(float c_maximal) {
        this.c_maximal = c_maximal;
    }

    public float getVolumep() {
        return volumep;
    }

    public void setVolumep(float volumep) {
        this.volumep = volumep;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
