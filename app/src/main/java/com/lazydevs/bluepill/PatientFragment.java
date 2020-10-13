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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PatientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PatientFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public PatientFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    PatientAdapter patientAdapter;

    ImageView empty;
    TextView empty2;

    DataBaseHelper dataBaseHelper;
    ArrayList<Integer> idL;
    ArrayList<String> nomL, prenomL;
    ArrayList<Float> tailleL,poidsL, scL;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient, container, false);
        final Button add_button = view.findViewById(R.id.addPatient);

        empty = view.findViewById(R.id.empty);
        empty2 = view.findViewById(R.id.empty2);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPatientActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        dataBaseHelper = new DataBaseHelper(view.getContext());
        idL = new ArrayList<>();
        nomL = new ArrayList<>();
        prenomL = new ArrayList<>();
        tailleL = new ArrayList<>();
        poidsL = new ArrayList<>();
        scL = new ArrayList<>();

        storeDatainArray();

        patientAdapter = new PatientAdapter(view.getContext(),idL, nomL, prenomL, tailleL, poidsL,scL);
        recyclerView.setAdapter(patientAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return  view;
    }

    void storeDatainArray(){
        Cursor cursor = dataBaseHelper.readAllDataP();
        if (cursor.getCount() == 0){
            empty.setVisibility(View.VISIBLE);
            empty2.setVisibility(View.VISIBLE);
        }else {
            while (cursor.moveToNext()){
                idL.add(cursor.getInt(0));
                nomL.add(cursor.getString(1));
                prenomL.add(cursor.getString(2));
                tailleL.add(cursor.getFloat(3));
                poidsL.add(cursor.getFloat(4));
                scL.add(cursor.getFloat(5));
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
