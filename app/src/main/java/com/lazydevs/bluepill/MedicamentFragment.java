package com.lazydevs.bluepill;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MedicamentFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public MedicamentFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    MedicamentAdapter medicamentAdapter;
    ImageView empty;
    TextView empty2;

    DataBaseHelper dataBaseHelper;
    ArrayList<Integer> idL;
    ArrayList<String> nomL, laboL, presentationL;
    ArrayList<Float> ciL,cminL, cmaxL, volumeL, prixL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicament, container, false);
        final Button add_button = view.findViewById(R.id.addMedicament);

        empty = view.findViewById(R.id.empty);
        empty2 = view.findViewById(R.id.empty2);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddMedicamentActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        dataBaseHelper = new DataBaseHelper(view.getContext());
        idL = new ArrayList<>();
        nomL = new ArrayList<>();
        laboL = new ArrayList<>();
        presentationL = new ArrayList<>();
        ciL = new ArrayList<>();
        cminL = new ArrayList<>();
        cmaxL = new ArrayList<>();
        volumeL = new ArrayList<>();
        prixL = new ArrayList<>();

        storeDatainArray();

        medicamentAdapter = new MedicamentAdapter(view.getContext(),idL, nomL, laboL, presentationL, ciL,cminL,cmaxL,volumeL,prixL);
        recyclerView.setAdapter(medicamentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        return view;
    }

    void storeDatainArray(){
        Cursor cursor = dataBaseHelper.readAllDataM();
        if (cursor.getCount() == 0){
            empty.setVisibility(View.VISIBLE);
            empty2.setVisibility(View.VISIBLE);
        }else {
            while (cursor.moveToNext()){
                idL.add(cursor.getInt(0));
                nomL.add(cursor.getString(1));
                laboL.add(cursor.getString(2));
                presentationL.add(cursor.getString(3));
                ciL.add(cursor.getFloat(4));
                cminL.add(cursor.getFloat(5));
                cmaxL.add(cursor.getFloat(6));
                volumeL.add(cursor.getFloat(7));
                prixL.add(cursor.getFloat(8));
            }
            empty.setVisibility(View.GONE);
            empty2.setVisibility(View.GONE);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
