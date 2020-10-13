package com.lazydevs.bluepill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity  implements MedicamentFragment.OnFragmentInteractionListener,PatientFragment.OnFragmentInteractionListener,HistoriqueFragment.OnFragmentInteractionListener,CalculFragment.OnFragmentInteractionListener {

    private BottomNavigationView bottomNavigationView;
    Button reliquat ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar ctoolbar =  (Toolbar) findViewById(R.id.ctoolbar);
        setSupportActionBar(ctoolbar);
        reliquat = findViewById(R.id.reliquat);
        bottomNavigationView = findViewById(R.id.bottomNav);


        loadFragment(new MedicamentFragment());

        Intent i = getIntent();
        String fragmentName = i.getStringExtra("fragment");
        String patient = "patient";
        String medicament = "medicament";
        Log.e("Test1", "Test1" + fragmentName);
        if (fragmentName != null && fragmentName.equals(patient)) {
            loadFragment(new PatientFragment());
        }else if (fragmentName != null && fragmentName.equals(medicament)){
            loadFragment(new MedicamentFragment());
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        reliquat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReliquatActivity.class);
                startActivity(intent);
            }
        });
    }



    public BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.medicament:
                    loadFragment(new MedicamentFragment());
                    return true;

                case R.id.patient:
                    loadFragment(new PatientFragment());
                    return true;

                case R.id.calcul:
                    loadFragment(new CalculFragment());
                    return true;

                case R.id.historique:
                    loadFragment(new HistoriqueFragment());
                    return true;

            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
