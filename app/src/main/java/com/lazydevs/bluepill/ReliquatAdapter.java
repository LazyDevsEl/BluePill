package com.lazydevs.bluepill;

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

public class ReliquatAdapter extends RecyclerView.Adapter<ReliquatAdapter.ReliquatHolder> {

    private Context context;
    private ArrayList idrL, medicament_nomL, reliquatL, montantL, perimeL;

    public ReliquatAdapter(Context context,
                          ArrayList  idrL,
                          ArrayList medicament_nomL,
                          ArrayList reliquatL,
                          ArrayList montantL,
                          ArrayList perimeL) {
        this.context = context;
        this.idrL = idrL;
        this.medicament_nomL = medicament_nomL;
        this.reliquatL = reliquatL;
        this.montantL = montantL;
        this.perimeL = perimeL;
    }

    @NonNull
    @Override
    public ReliquatAdapter.ReliquatHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardr,viewGroup,false);

        return new ReliquatAdapter.ReliquatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReliquatAdapter.ReliquatHolder holder, final int position) {
        holder.idr_txt.setText(String.valueOf(idrL.get(position)));
        holder.medicament_nom_txt.setText(String.valueOf(medicament_nomL.get(position)));
        holder.reliquat_txt.setText(String.valueOf(reliquatL.get(position)));
        holder.montant_txt.setText(String.valueOf(montantL.get(position)));
        holder.perime_txt.setText(String.valueOf(perimeL.get(position)));

    }


    @Override
    public int getItemCount() {

        return idrL.size();
    }

    public class ReliquatHolder extends RecyclerView.ViewHolder {

        TextView idr_txt, medicament_nom_txt, reliquat_txt, montant_txt, perime_txt;

        public ReliquatHolder(@NonNull View itemView) {
            super(itemView);
            idr_txt = itemView.findViewById(R.id.idr);
            medicament_nom_txt = itemView.findViewById(R.id.nom_medicament);
            reliquat_txt = itemView.findViewById(R.id.reliquat_m);
            montant_txt = itemView.findViewById(R.id.montant);
            perime_txt = itemView.findViewById(R.id.status);
        }
    }
}