package com.lazydevs.bluepill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoriqueAdapter extends RecyclerView.Adapter<HistoriqueAdapter.HistoriqueHolder> {
    private Context context;
    private ArrayList nomPL,prenomPL,nomML,doseL,volumeL,flaconL,reliquatL,reliquat_finalL,pocheL;

    public HistoriqueAdapter(Context context, ArrayList nomPL,ArrayList prenomPL, ArrayList nomML, ArrayList doseL, ArrayList volumeL, ArrayList flaconL, ArrayList reliquatL, ArrayList reliquat_finalL, ArrayList pocheL) {
        this.context = context;
        this.nomPL = nomPL;
        this.prenomPL = prenomPL;
        this.nomML = nomML;
        this.doseL = doseL;
        this.volumeL = volumeL;
        this.flaconL = flaconL;
        this.reliquatL = reliquatL;
        this.reliquat_finalL = reliquat_finalL;
        this.pocheL = pocheL;
    }

    @NonNull
    @Override
    public HistoriqueAdapter.HistoriqueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardh,parent,false);

        return new HistoriqueHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriqueAdapter.HistoriqueHolder holder, int position) {
        holder.nomP.setText(String.valueOf(nomPL.get(position)));
        holder.prenomP.setText(String.valueOf(prenomPL.get(position)));
        holder.nomM.setText(String.valueOf(nomML.get(position)));
        holder.dose.setText(String.valueOf(doseL.get(position)));
        holder.volume.setText(String.valueOf(volumeL.get(position)));
        holder.flacon.setText(String.valueOf(flaconL.get(position)));
        holder.reliquat.setText(String.valueOf(reliquatL.get(position)));
        holder.reliquat_final.setText(String.valueOf(reliquat_finalL.get(position)));
        holder.poche.setText(String.valueOf(pocheL.get(position)));
    }

    @Override
    public int getItemCount() {
        return nomPL.size();
    }


    public class HistoriqueHolder extends RecyclerView.ViewHolder {

        TextView nomP,prenomP, nomM, dose,volume,flacon,reliquat,reliquat_final,poche;
        public HistoriqueHolder(@NonNull View itemView) {
            super(itemView);
            nomP = itemView.findViewById(R.id.nom_patient);
            prenomP = itemView.findViewById(R.id.prenom);
            nomM = itemView.findViewById(R.id.nom_medicament);
            dose = itemView.findViewById(R.id.dose);
            volume = itemView.findViewById(R.id.volume);
            flacon = itemView.findViewById(R.id.flacon);
            reliquat = itemView.findViewById(R.id.reliquat);
            reliquat_final = itemView.findViewById(R.id.reliquat_final);
            poche = itemView.findViewById(R.id.poche);
        }
    }
}
