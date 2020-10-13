package com.lazydevs.bluepill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ReliquatActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DataBaseHelper dataBaseHelper;
    ReliquatAdapter reliquatAdapter;

    ArrayList<Integer> idrL,perimeL,montantL;
    ArrayList<String> medicamon_nomL;
    ArrayList<Float> reliquatL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reliquat);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        dataBaseHelper = new DataBaseHelper(ReliquatActivity.this);
        idrL = new ArrayList<>();
        perimeL = new ArrayList<>();
        montantL = new ArrayList<>();
        medicamon_nomL = new ArrayList<>();
        reliquatL = new ArrayList<>();

        storeDatainArray();

        reliquatAdapter = new ReliquatAdapter(ReliquatActivity.this,idrL,medicamon_nomL,reliquatL,montantL,perimeL);
        recyclerView.setAdapter(reliquatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReliquatActivity.this));
    }

    void storeDatainArray(){
        Cursor cursor = dataBaseHelper.readAllDataR();
        if (cursor.getCount() == 0){
            //empty.setVisibility(View.VISIBLE);
            //empty2.setVisibility(View.VISIBLE);
        }else {
            while (cursor.moveToNext()){
                idrL.add(cursor.getInt(0));
                medicamon_nomL.add(cursor.getString(1));
                reliquatL.add(cursor.getFloat(2));
                montantL.add(cursor.getInt(3));
                perimeL.add(cursor.getInt(4));
            }
            //empty.setVisibility(View.GONE);
            //empty2.setVisibility(View.GONE);
        }
    }

}
