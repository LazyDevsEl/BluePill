package com.lazydevs.bluepill;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifierPatientActivity extends AppCompatActivity {

    EditText nom2,prenom2,taille2,poids2;
    TextView id2;
    Button modifier, supprimer;

    String id,nom,prenom,taille,poids;
    float sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_patient);
        id2 = findViewById(R.id.id2);
        nom2 = findViewById(R.id.nom2);
        prenom2 = findViewById(R.id.prenom2);
        taille2 = findViewById(R.id.taille2);
        poids2 = findViewById(R.id.poids2);
        modifier = findViewById(R.id.modifier);
        supprimer = findViewById(R.id.supprimer);

        getAndsetIntentData();

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper =new DataBaseHelper(ModifierPatientActivity.this);
                if ((nom2.getText().toString().matches(""))||(prenom2.getText().toString().matches(""))||(taille2.getText().toString().matches(""))||(poids2.getText().toString().matches(""))) {
                    Toast.makeText(ModifierPatientActivity.this, "vide", Toast.LENGTH_SHORT).show();
                    return;
                }
                    nom = nom2.getText().toString().trim();
                    prenom = prenom2.getText().toString().trim();
                    taille = taille2.getText().toString().trim();
                    poids = poids2.getText().toString().trim();
                    float p,t,s;
                    p = Float.parseFloat(poids);
                    t = Float.parseFloat(taille)*100;
                    s = p*t;
                    sc = (float) (((s))/3600);
                    dataBaseHelper.updateDataPatient(id,nom,prenom,taille,poids,sc);
                    dataBaseHelper.close();
                    Intent intent = new Intent(ModifierPatientActivity.this, MainActivity.class);
                    String fragmnet = "patient";
                    intent.putExtra("fragment", fragmnet);
                    intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    



            }
        });

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }
    void getAndsetIntentData(){
        if (getIntent().hasExtra("id")&&getIntent().hasExtra("nom") && getIntent().hasExtra("prenom") && getIntent().hasExtra("taille") && getIntent().hasExtra("poids")   ){
            id = getIntent().getStringExtra("id");
            nom = getIntent().getStringExtra("nom");
            prenom = getIntent().getStringExtra("prenom");
            taille= getIntent().getStringExtra("taille");
            poids = getIntent().getStringExtra("poids");


            nom2.setText(nom);
            prenom2.setText(prenom);
            taille2.setText(taille);
            poids2.setText(poids);


        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer " + nom + " ?");
        builder.setMessage("Etes-vous sûr que vous voulez supprimer " + nom + "?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(ModifierPatientActivity.this);
                dataBaseHelper.deletePatient(id);
                dataBaseHelper.close();
                Intent intent = new Intent(ModifierPatientActivity.this, MainActivity.class);
                String fragmnet = "patient";
                intent.putExtra("fragment", fragmnet);
                intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
