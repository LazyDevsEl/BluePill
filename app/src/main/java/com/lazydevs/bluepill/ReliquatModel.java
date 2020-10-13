package com.lazydevs.bluepill;

public class ReliquatModel {
    private int idr;
    private String medicament_nom;
    private float reliquat;
    private int montant;
    private int preime;

    public ReliquatModel(int idr, String medicament_nom, float reliquat, int montant, int preime) {
        this.medicament_nom = medicament_nom;
        this.reliquat = reliquat;
        this.montant = montant;
        this.preime = preime;
        this.idr = idr;
    }

    public ReliquatModel() {
    }

    public String getMedicament_nom() {
        return medicament_nom;
    }

    public void setMedicament_nom(String medicament_nom) {
        this.medicament_nom = medicament_nom;
    }

    public float getReliquat() {
        return reliquat;
    }

    public void setReliquat(float reliquat) {
        this.reliquat = reliquat;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getPreime() {
        return preime;
    }

    public void setPreime(int preime) {
        this.preime = preime;
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }
}
