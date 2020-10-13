package com.lazydevs.bluepill;

public class CalculModel {
    private String nom;
    private String prenom;
    private String nom_medicament;
    private float dose;
    private float volume;
    private float flacon;
    private float reliquat;
    private float reliquat_final;
    private float poche;

    public CalculModel(String nom, String prenom, String nom_medicament, float dose, float volume, float flacon, float reliquat, float reliquat_final, float poche) {
        this.nom = nom;
        this.prenom = prenom;
        this.nom_medicament = nom_medicament;
        this.dose = dose;
        this.volume = volume;
        this.flacon = flacon;
        this.reliquat = reliquat;
        this.reliquat_final = reliquat_final;
        this.poche = poche;
    }

    public CalculModel() {
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

    public String getNom_medicament() {
        return nom_medicament;
    }

    public void setNom_medicament(String nom_medicament) {
        this.nom_medicament = nom_medicament;
    }

    public float getDose() {
        return dose;
    }

    public void setDose(float dose) {
        this.dose = dose;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getFlacon() {
        return flacon;
    }

    public void setFlacon(float flacon) {
        this.flacon = flacon;
    }

    public float getReliquat() {
        return reliquat;
    }

    public void setReliquat(float reliquat) {
        this.reliquat = reliquat;
    }

    public float getReliquat_final() {
        return reliquat_final;
    }

    public void setReliquat_final(float reliquat_final) {
        this.reliquat_final = reliquat_final;
    }

    public float getPoche() {
        return poche;
    }

    public void setPoche(float poche) {
        this.poche = poche;
    }
}
