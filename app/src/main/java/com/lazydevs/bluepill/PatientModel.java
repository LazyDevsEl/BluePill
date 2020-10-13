package com.lazydevs.bluepill;

public class PatientModel {
    private int id;
    private String nom;
    private String prenom;
    private float taille;
    private float poids;
    private float sc;

    public PatientModel(int id, String nom, String prenom, float taille, float poids, float sc) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.taille = taille;
        this.poids = poids;
        this.sc = sc;
    }

    public PatientModel() {
    }

    @Override
    public String toString() {
        return "PatientModel{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", taille=" + taille +
                ", poids=" + poids +
                ", sc=" + sc +
                '}';
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getSc() {
        return sc;
    }

    public void setSc(float sc) {
        this.sc = sc;
    }
}
