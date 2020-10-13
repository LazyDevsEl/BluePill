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

public class ModifierMedicamentActivity extends AppCompatActivity {
    EditText nom2, laboratoire2,presentation2,c_initail2,c_minimal2,c_maximal2,prix2;
    TextView id2;
    Button modifier,supprimer;

    String id,nom, laboratoire,presentation,c_initail,c_minimal,c_maximal,prix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_medicament);
        id2 =findViewById(R.id.id2);
        nom2 =findViewById(R.id.nom2);
        laboratoire2 =findViewById(R.id.labo2);
        presentation2 =findViewById(R.id.presentastion2);
        c_initail2 =findViewById(R.id.c_initial2);
        c_minimal2 =findViewById(R.id.c_minimal2);
        c_maximal2 =findViewById(R.id.c_maximal2);
        prix2 =findViewById(R.id.prix2);
        modifier = findViewById(R.id.modifier);
        supprimer = findViewById(R.id.supprimer);
        getAndsetIntentData();

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper =new DataBaseHelper(ModifierMedicamentActivity.this);
                if ((nom2.getText().toString().matches(""))||(laboratoire2.getText().toString().matches(""))||(presentation2.getText().toString().matches(""))||(c_initail2.getText().toString().matches(""))||(c_minimal2.getText().toString().matches(""))||(c_maximal2.getText().toString().matches(""))||(prix2.getText().toString().matches(""))) {
                    Toast.makeText(ModifierMedicamentActivity.this, "vide", Toast.LENGTH_SHORT).show();
                    return;
                }
                nom = nom2.getText().toString().trim();
                laboratoire = laboratoire2.getText().toString().trim();
                presentation = presentation2.getText().toString().trim();
                c_initail = c_initail2.getText().toString().trim();
                c_minimal = c_minimal2.getText().toString().trim();
                c_maximal = c_maximal2.getText().toString().trim();
                prix = prix2.getText().toString().trim();

                dataBaseHelper.updateDataMedicament(id,nom, laboratoire,presentation,c_initail,c_minimal,c_maximal,prix);
                dataBaseHelper.close();

                Intent intent = new Intent(ModifierMedicamentActivity.this, MainActivity.class);
                String fragmnet = "medicament";
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
        if (getIntent().hasExtra("id")&&getIntent().hasExtra("nom") && getIntent().hasExtra("laboratoire") && getIntent().hasExtra("presentation") && getIntent().hasExtra("ci") && getIntent().hasExtra("cmin") && getIntent().hasExtra("cmax") && getIntent().hasExtra("prix") ){
            id = getIntent().getStringExtra("id");
            nom = getIntent().getStringExtra("nom");
            laboratoire = getIntent().getStringExtra("laboratoire");
            presentation= getIntent().getStringExtra("presentation");
            c_initail = getIntent().getStringExtra("ci");
            c_minimal = getIntent().getStringExtra("cmin");
            c_maximal = getIntent().getStringExtra("cmax");
            prix = getIntent().getStringExtra("prix");



            nom2.setText(nom);
            laboratoire2.setText(laboratoire);
            presentation2.setText(presentation);
            c_initail2.setText(c_initail);
            c_minimal2.setText(c_minimal);
            c_maximal2.setText(c_maximal);
            prix2.setText(prix);


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
                DataBaseHelper dataBaseHelper = new DataBaseHelper(ModifierMedicamentActivity.this);
                dataBaseHelper.deleteMedicament(id);
                dataBaseHelper.close();

                Intent intent = new Intent(ModifierMedicamentActivity.this, MainActivity.class);
                String fragmnet = "medicament";
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
