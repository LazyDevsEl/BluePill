package com.lazydevs.bluepill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPatientActivity extends Activity {

    EditText id,nom,prenom,taille,poids;
    Button confirmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        taille = findViewById(R.id.taille);
        poids = findViewById(R.id.poids);
        confirmer = findViewById(R.id.confirmer);

        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientModel patientModel;
                if ((nom.getText().toString().matches(""))||(prenom.getText().toString().matches(""))||(taille.getText().toString().matches(""))||(poids.getText().toString().matches(""))) {
                    Toast.makeText(AddPatientActivity.this, "vide", Toast.LENGTH_SHORT).show();
                    return;
                }
                float t,p,s;
                p = Float.parseFloat(poids.getText().toString());
                t = Float.parseFloat(taille.getText().toString())*100;
                s = p*t;
                float sc = (float) ((s)/3600);
                try {
                    patientModel = new PatientModel(-1,nom.getText().toString(),
                            prenom.getText().toString(),
                            Float.parseFloat(taille.getText().toString()),
                            Float.parseFloat(poids.getText().toString()),
                            sc);
                    Toast.makeText(AddPatientActivity.this,patientModel.toString(),Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(AddPatientActivity.this,"erreur",Toast.LENGTH_SHORT).show();
                    patientModel = new PatientModel(-1,"Nom",
                            "Prenom",
                            0,
                            0,
                            0);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddPatientActivity.this);
                boolean success = dataBaseHelper.addOneP(patientModel);
                Toast.makeText(AddPatientActivity.this,"success = "+success,Toast.LENGTH_SHORT).show();
                dataBaseHelper.close();

                Intent intent = new Intent(AddPatientActivity.this, MainActivity.class);
                String fragmnet = "patient";
                intent.putExtra("fragment", fragmnet);
                intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
