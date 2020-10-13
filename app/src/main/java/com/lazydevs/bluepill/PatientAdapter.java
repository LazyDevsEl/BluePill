package com.lazydevs.bluepill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {

    private Context context;
    private ArrayList  idL, nomL, prenomL, tailleL, poidsL,scL;

    public PatientAdapter(Context context,
                          ArrayList  idL,
                          ArrayList nomL,
                          ArrayList prenomL,
                          ArrayList tailleL,
                          ArrayList poidsL,
                          ArrayList scL) {
        this.context = context;
        this.idL = idL;
        this.nomL = nomL;
        this.prenomL = prenomL;
        this.tailleL = tailleL;
        this.poidsL = poidsL;
        this.scL = scL;
    }

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardp,viewGroup,false);

        return new PatientHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.PatientHolder holder, final int position) {
        holder.id_txt.setText(String.valueOf(idL.get(position)));
        holder.nom_txt.setText(String.valueOf(nomL.get(position)));
        holder.prenom_txt.setText(String.valueOf(prenomL.get(position)));
        holder.taille_txt.setText(String.valueOf(tailleL.get(position)));
        holder.poids_txt.setText(String.valueOf(poidsL.get(position)));
        holder.sc_tx.setText(String.valueOf(scL.get(position)));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ModifierPatientActivity.class);
                intent.putExtra("id",String.valueOf(idL.get(position)));
                intent.putExtra("nom",String.valueOf(nomL.get(position)));
                intent.putExtra("prenom",String.valueOf(prenomL.get(position)));
                intent.putExtra("taille",String.valueOf(tailleL.get(position)));
                intent.putExtra("poids",String.valueOf(poidsL.get(position)));
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {

        return idL.size();
    }

    public class PatientHolder extends RecyclerView.ViewHolder {

        TextView id_txt, nom_txt, prenom_txt, taille_txt, poids_txt,sc_tx;
        RelativeLayout relativeLayout;
        public PatientHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.num);
            nom_txt = itemView.findViewById(R.id.nom);
            prenom_txt = itemView.findViewById(R.id.prenom);
            taille_txt = itemView.findViewById(R.id.taille);
            poids_txt = itemView.findViewById(R.id.poids);
            sc_tx = itemView.findViewById(R.id.sc);
            relativeLayout = itemView.findViewById(R.id.relative_layout);


        }
    }
}
