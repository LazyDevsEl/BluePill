package com.lazydevs.bluepill;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistoriqueFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HistoriqueFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public HistoriqueFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    HistoriqueAdapter historiqueAdapter;
    DataBaseHelper dataBaseHelper;

    ArrayList<String> nomPL,prenomL,nomML;
    ArrayList<Float> doseL,volumeL,flaconL,reliquatL,reliquat_finalL,pocheL;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historique, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        dataBaseHelper = new DataBaseHelper(view.getContext());
        nomPL = new ArrayList<>();
        prenomL = new ArrayList<>();
        nomML = new ArrayList<>();
        doseL = new ArrayList<>();
        volumeL = new ArrayList<>();
        flaconL = new ArrayList<>();
        reliquatL = new ArrayList<>();
        reliquat_finalL = new ArrayList<>();
        pocheL = new ArrayList<>();

        storeDatainArray();


        historiqueAdapter= new HistoriqueAdapter(view.getContext(),nomPL, prenomL,  nomML,  doseL,  volumeL,  flaconL,  reliquatL,  reliquat_finalL,  pocheL);
        recyclerView.setAdapter(historiqueAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }
    void storeDatainArray(){
        Cursor cursor = dataBaseHelper.readAllDataC();
        try {
            while( cursor != null && cursor.moveToNext() ){
                nomPL.add(cursor.getString(0));
                prenomL.add(cursor.getString(1));
                nomML.add(cursor.getString(2));
                doseL.add(cursor.getFloat(3));
                volumeL.add(cursor.getFloat(4));
                flaconL.add(cursor.getFloat(5));
                reliquatL.add(cursor.getFloat(6));
                reliquat_finalL.add(cursor.getFloat(7));
                pocheL.add(cursor.getFloat(8));

            }
        }catch (Exception e){

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
