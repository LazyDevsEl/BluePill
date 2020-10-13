package com.lazydevs.bluepill;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CalculFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public CalculFragment() {

    }
    ScrollView scrollView;
    RelativeLayout relativeLayout,stability_layout;
    CalculModel calculModel;
    ReliquatModel reliquatModel;

    EditText id_p,medicament_nom,posologie;
    TextView dose,volume,flacon,reliquat,reliquat_final,poche;

    RelativeLayout resultat;

    Spinner lumiere,type_flacon;
    EditText temp;
    Button stability;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_calcul, container, false);
        final Button calcul = view.findViewById(R.id.calcul);

        id_p = view.findViewById(R.id.num_patient);
        medicament_nom = view.findViewById(R.id.nom_medicament);
        posologie = view.findViewById(R.id.posologie);
        dose = view.findViewById(R.id.dose);
        volume = view.findViewById(R.id.volume);
        flacon = view.findViewById(R.id.flacon);
        reliquat = view.findViewById(R.id.reliquat);
        reliquat_final = view.findViewById(R.id.reliquat_final);
        poche = view.findViewById(R.id.poche);

        relativeLayout =view.findViewById(R.id.relative_layout);

        stability_layout =view.findViewById(R.id.stability_layout);

        resultat = view.findViewById(R.id.resultat);

        type_flacon = view.findViewById(R.id.type_flacon);
        lumiere =  view.findViewById(R.id.lumiere);
        temp =  view.findViewById(R.id.temp);
        stability = view.findViewById(R.id.stability);


        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = null;
                String prenom = null;
                String nomM ;
                float posologieV,reliquatC = 0,reliquatFinal = 0;
                float doseAdmi = -1;
                float volumeFinal = -1;
                int flaconC = 0;
                float pocheC = 0;

                final int perime = 0;
                DataBaseHelper data = new DataBaseHelper(view.getContext());
                if ((id_p.getText().toString().matches(""))||(medicament_nom.getText().toString().matches(""))||(posologie.getText().toString().matches(""))) {
                    Toast.makeText(view.getContext(), "vide", Toast.LENGTH_SHORT).show();
                    return;
                }
                int idp = Integer.parseInt(id_p.getText().toString());
                nomM= medicament_nom.getText().toString();
                posologieV = Float.parseFloat(posologie.getText().toString());
                try {


                    if ((idp != -1) && (nomM != null) && (posologieV != -1)) {

                        float sc = data.getPatientSC(idp);
                        float c_initial = data.getMedicamentCI(nomM);

                        nom = data.getPatientNom(idp);
                        prenom = data.getPatientPrenom(idp);

                        doseAdmi = sc * posologieV;
                        volumeFinal = doseAdmi / c_initial;

                        float presentation = data.getMedicamentPresentation(nomM);
                        if ((int) (volumeFinal / presentation) == 0) {
                            flaconC = 1;
                        } else {
                            flaconC = (int) (volumeFinal / presentation) + 1;
                        }
                        reliquatC = (flaconC * presentation) - volumeFinal;
                        reliquatFinal = reliquatFinal + reliquatC;

                        float c_minimal, c_maximal;
                        c_minimal = data.getMedicamentCMin(nomM);
                        c_maximal = data.getMedicamentCMax(nomM);
                        float parMin, parMax;
                        parMin = c_minimal * 250;
                        parMax = c_maximal * 250;
                        if ((doseAdmi > parMin) && (doseAdmi < parMax)) {
                            pocheC = 250;
                        } else {
                            parMin = c_minimal * 500;
                            parMax = c_maximal * 500;
                            if ((doseAdmi > parMin) && (doseAdmi < parMax)) {
                                pocheC = 500;
                            } else {
                                parMin = c_minimal * 1000;
                                parMax = c_maximal * 1000;
                                if ((doseAdmi > parMin) && (doseAdmi < parMax)) {
                                    pocheC = 1000;
                                } else {
                                    pocheC = c_minimal;
                                }
                            }
                        }

                    }
                }catch (Exception e){
                    Toast.makeText(view.getContext(), "Erreur", Toast.LENGTH_SHORT).show();
                }
                data.close();


                calculModel = new CalculModel(nom, prenom, nomM, doseAdmi, volumeFinal, flaconC, reliquatC, reliquatFinal, pocheC);

                dose.setText(String.valueOf(calculModel.getDose()));
                volume.setText(String.valueOf(calculModel.getVolume()));
                flacon.setText(String.valueOf(calculModel.getFlacon()));
                reliquat.setText(String.valueOf(calculModel.getReliquat()));
                reliquat_final.setText(String.valueOf(calculModel.getReliquat_final()));
                poche.setText(String.valueOf(calculModel.getPoche()));

                relativeLayout.setVisibility(View.VISIBLE);
                stability_layout.setVisibility(View.VISIBLE);



                DataBaseHelper dataBaseHelper = new DataBaseHelper(view.getContext());
                boolean success = dataBaseHelper.addOneC(calculModel);
                Toast.makeText(view.getContext(),"success = "+success,Toast.LENGTH_SHORT).show();
                dataBaseHelper.close();

            }
        });
        stability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper data = new DataBaseHelper(view.getContext());
                String flaconS = type_flacon.getSelectedItem().toString();
                String lumiereS = lumiere.getSelectedItem().toString();
                if ((temp.getText().toString().matches(""))) {
                    Toast.makeText(view.getContext(), "vide", Toast.LENGTH_SHORT).show();
                    return;
                }
                int temperature = Integer.parseInt(temp.getText().toString());
                int perimer = 0;
                if (flaconS.equals("verre")){
                    if (lumiereS.equals("sans")){
                        perimer = 60- temperature;
                    }else if (lumiereS.equals("avec")){
                        perimer = 45- temperature;
                    }
                }else if (flaconS.equals("pvc")){
                    if (lumiereS.equals("sans")){
                        perimer = 56- temperature;
                    }else if (lumiereS.equals("avec")){
                        perimer = 86- temperature;
                    }
                }
                String nomMS = medicament_nom.getText().toString();
                float reliquatS = Float.parseFloat(reliquat.getText().toString());

                int prixmg = (int) data.getMedicamentPrix(nomMS);
                int montant = (int) (prixmg * reliquatS);
                reliquatModel = new ReliquatModel(-1,nomMS,reliquatS,montant,perimer);

                DataBaseHelper dataBaseHelper = new DataBaseHelper(view.getContext());
                boolean success = dataBaseHelper.addOneR(reliquatModel);
                Toast.makeText(view.getContext(),"success = "+success,Toast.LENGTH_SHORT).show();
                dataBaseHelper.close();
            }


        });

        return view;
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
