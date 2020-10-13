package com.lazydevs.bluepill;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PatientHolder extends RecyclerView.ViewHolder {

    TextView nom;
    TextView prenom;
    TextView taille;
    TextView poids;
    TextView sc;

    public PatientHolder(@NonNull View itemView) {
        super(itemView);

        this.nom = itemView.findViewById(R.id.nom);
        this.prenom = itemView.findViewById(R.id.prenom);
        this.taille = itemView.findViewById(R.id.taille);
        this.poids = itemView.findViewById(R.id.poids);
        this.sc = itemView.findViewById(R.id.sc);
    }
}
