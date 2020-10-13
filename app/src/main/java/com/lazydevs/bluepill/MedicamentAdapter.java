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

public class MedicamentAdapter extends RecyclerView.Adapter<MedicamentAdapter.MedicamentHolder> {

    private Context context;
    private ArrayList idL, nomL, laboL,presentationL, ciL, cminL,cmaxL,volumeL, prixL;

    public MedicamentAdapter(Context context, ArrayList idL, ArrayList nomL, ArrayList laboL, ArrayList presentationL, ArrayList ciL, ArrayList cminL, ArrayList cmaxL, ArrayList volumeL, ArrayList prixL) {
        this.context = context;
        this.idL = idL;
        this.nomL = nomL;
        this.laboL = laboL;
        this.presentationL = presentationL;
        this.ciL = ciL;
        this.cminL = cminL;
        this.cmaxL = cmaxL;
        this.volumeL = volumeL;
        this.prixL = prixL;
    }

    @NonNull
    @Override
    public MedicamentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardm,parent,false);

        return new MedicamentAdapter.MedicamentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentHolder holder, final int position) {
        holder.id_txt.setText(String.valueOf(idL.get(position)));
        holder.nom_txt.setText(String.valueOf(nomL.get(position)));
        holder.labo_txt.setText(String.valueOf(laboL.get(position)));
        holder.presentation_txt.setText(String.valueOf(presentationL.get(position)));
        holder.ci_txt.setText(String.valueOf(ciL.get(position)));
        holder.cmin_tx.setText(String.valueOf(cminL.get(position)));
        holder.cmax_tx.setText(String.valueOf(cmaxL.get(position)));
        holder.volume_tx.setText(String.valueOf(volumeL.get(position)));
        holder.prix_txt.setText(String.valueOf(prixL.get(position)));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ModifierMedicamentActivity.class);
                intent.putExtra("id",String.valueOf(idL.get(position)));
                intent.putExtra("nom",String.valueOf(nomL.get(position)));
                intent.putExtra("laboratoire",String.valueOf(laboL.get(position)));
                intent.putExtra("presentation",String.valueOf(presentationL.get(position)));
                intent.putExtra("ci",String.valueOf(ciL.get(position)));
                intent.putExtra("cmin",String.valueOf(cminL.get(position)));
                intent.putExtra("cmax",String.valueOf(cmaxL.get(position)));
                intent.putExtra("prix",String.valueOf(prixL.get(position)));


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return idL.size();
    }

    public class MedicamentHolder extends RecyclerView.ViewHolder {
        TextView id_txt, nom_txt, labo_txt, presentation_txt, ci_txt,cmin_tx,cmax_tx,volume_tx,prix_txt;
        RelativeLayout relativeLayout;
        public MedicamentHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id);
            nom_txt = itemView.findViewById(R.id.nom);
            labo_txt = itemView.findViewById(R.id.labo);
            presentation_txt = itemView.findViewById(R.id.pres1);
            ci_txt = itemView.findViewById(R.id.ci);
            cmin_tx = itemView.findViewById(R.id.cmin);
            cmax_tx = itemView.findViewById(R.id.cmax);
            volume_tx = itemView.findViewById(R.id.volumeap);
            prix_txt = itemView.findViewById(R.id.prix);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
