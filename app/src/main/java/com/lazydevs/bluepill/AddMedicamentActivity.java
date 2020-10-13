package com.lazydevs.bluepill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMedicamentActivity extends AppCompatActivity {

    EditText nom, laboratoire,presentation,c_initail,c_minimal,c_maximal,prix;
    Button confirmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicament);

        nom = findViewById(R.id.nom);
        laboratoire = findViewById(R.id.labo);
        presentation = findViewById(R.id.presentastion);
        c_initail = findViewById(R.id.c_initial);
        c_minimal = findViewById(R.id.c_minimal);
        c_maximal = findViewById(R.id.c_maximal);
        prix = findViewById(R.id.prix);
        confirmer = findViewById(R.id.confirmer);

        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicamentModel medicamentModel;
                if ((nom.getText().toString().matches(""))||(laboratoire.getText().toString().matches(""))||(presentation.getText().toString().matches(""))||(c_initail.getText().toString().matches(""))||(c_minimal.getText().toString().matches(""))||(c_maximal.getText().toString().matches(""))||(prix.getText().toString().matches(""))) {
                    Toast.makeText(AddMedicamentActivity.this, "vide", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    medicamentModel = new MedicamentModel(-1,
                            nom.getText().toString(),
                            laboratoire.getText().toString(),
                            presentation.getText().toString(),
                            Float.parseFloat(c_initail.getText().toString()),
                            Float.parseFloat(c_minimal.getText().toString()),
                            Float.parseFloat(c_maximal.getText().toString()),
                            0,
                            Float.parseFloat(prix.getText().toString()));
                    Toast.makeText(AddMedicamentActivity.this,"success",Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(AddMedicamentActivity.this,"erreur",Toast.LENGTH_SHORT).show();
                    medicamentModel = new MedicamentModel(-1,"erreur", "erreur", "erreur", 0, 0,0,0,0);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddMedicamentActivity.this);
                boolean success = dataBaseHelper.addOneM(medicamentModel);
                Toast.makeText(AddMedicamentActivity.this,"success = "+success,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddMedicamentActivity.this, MainActivity.class);
                String fragmnet = "medicament";
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
