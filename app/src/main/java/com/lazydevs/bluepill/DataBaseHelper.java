package com.lazydevs.bluepill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String PATIENTS = "PATIENTS";
    public static final String IDP = "IDP";
    public static final String PATIENT_NOM = "PATIENT_NOM";
    public static final String PATIENT_PRENOM = "PATIENT_PRENOM";
    public static final String TAILLE = "TAILLE";
    public static final String POIDS = "POIDS";
    public static final String SURFACCE_CORPORELLE = "SURFACCE_CORPORELLE";

    public static final String MEDICAMENTS = "MEDICAMENTS";
    public static final String IDM = "IDM";
    public static final String MEDICAMENT_NOM = "MEDICAMENT_NOM";
    public static final String LABORATOIRE = "LABORATOIRE";
    public static final String PRESENTATION = "PESENTATION";
    public static final String C_INITIAL = "C_INITIAL";
    public static final String C_MINIMAL = "C_MINIMAL";
    public static final String C_MAXIMAL = "C_MAXIMAL";
    public static final String VOLUMEP = "VOLUMEP";
    public static final String PRIX = "PRIX";

    private static final String CALCULS = "CALCULS" ;
    public static final String IDH = "IDH";
    private static final String DOSE = "DOSE" ;
    private static final String VOLUME = "VOLUME" ;
    private static final String FLACON = "FLACON";
    private static final String RELIQUAT = "RELIQUAT" ;
    private static final String RELIQUAT_FINAL = "RELIQUAT_FINAL" ;
    private static final String POCHE = "POCHE" ;
    private static final String TAG = "DBhelper";

    private static final String RELIQUATS = "RELIQUATS";
    private static final String IDR = "IDR";
    private static final String MOTANT_PERDU = "MOTANT_PERDU";
    private static final String PERIME = "PERIME";


    private String createTableP = "CREATE TABLE " + PATIENTS + "("
            + IDP + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PATIENT_NOM + " TEXT NOT NULL, "
            + PATIENT_PRENOM + " TEXT NOT NULL, "
            + TAILLE + " FLOAT NOT NULL, "
            + POIDS + " FLOAT NOT NULL, "
            + SURFACCE_CORPORELLE + " FLOAT NOT NULL) ";

    private String createTableM = "CREATE TABLE " + MEDICAMENTS + "( "
            + IDM + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MEDICAMENT_NOM + " TEXT NOT NULL, "
            + LABORATOIRE + " TEXT NOT NULL, "
            + PRESENTATION + " TEXT NOT NULL, "
            + C_INITIAL + " FLOAT NOT NULL, "
            + C_MINIMAL + " FLOAT NOT NULL, "
            + C_MAXIMAL + " FLOAT NOT NULL, "
            + VOLUMEP + " FLOAT NOT NULL, "
            + PRIX + " FLOAT NOT NULL) ";

    private String createTableC = "CREATE TABLE " + CALCULS + "("
            + PATIENT_NOM + " TEXT NOT NULL, "
            + PATIENT_PRENOM + " TEXT NOT NULL, "
            + MEDICAMENT_NOM + " TEXT NOT NULL, "
            + DOSE + " FLOAT NOT NULL, "
            + VOLUME + " FLOAT NOT NULL, "
            + FLACON + " FLOAT NOT NULL, "
            + RELIQUAT + " FLOAT NOT NULL, "
            + RELIQUAT_FINAL + " FLOAT NOT NULL, "
            + POCHE + " FLOAT NOT NULL,"
            + " FOREIGN KEY ("+PATIENT_NOM+") REFERENCES "+PATIENTS+"("+PATIENT_NOM+"),"
            + " FOREIGN KEY ("+MEDICAMENT_NOM+") REFERENCES "+MEDICAMENTS+"("+MEDICAMENT_NOM+"),"
            + " FOREIGN KEY ("+PATIENT_PRENOM+") REFERENCES "+PATIENTS+"("+PATIENT_PRENOM+"))";

    private String createTableR = "CREATE TABLE " + RELIQUATS + "("
            + IDR + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MEDICAMENT_NOM + " TEXT NOT NULL, "
            + RELIQUAT + " FLOAT NOT NULL, "
            + MOTANT_PERDU + " FLOAT NOT NULL, "
            + PERIME + " INTEGER NOT NULL,"
            + " FOREIGN KEY ("+MEDICAMENT_NOM+") REFERENCES "+MEDICAMENTS+"("+MEDICAMENT_NOM+"))";


    public Context context;
    DataBaseHelper(@Nullable Context context) {
        super(context, "bluepill.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableP);
        db.execSQL(createTableM);
        db.execSQL(createTableC);
        db.execSQL(createTableR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,"updating database from version " + oldVersion + " to " + newVersion );
        db.execSQL("DROP TABLE IF EXISTS " + PATIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + CALCULS);
        db.execSQL("DROP TABLE IF EXISTS " + MEDICAMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + RELIQUATS);

        onCreate(db);

    }

    public boolean addOneP(PatientModel patientModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PATIENT_NOM,patientModel.getNom());
        cv.put(PATIENT_PRENOM,patientModel.getPrenom());
        cv.put(TAILLE,patientModel.getTaille());
        cv.put(POIDS,patientModel.getPoids());
        cv.put(SURFACCE_CORPORELLE,patientModel.getSc());

        long insert = db.insert(PATIENTS, null, cv);
        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean addOneM(MedicamentModel medicamentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MEDICAMENT_NOM,medicamentModel.getNom());
        cv.put(LABORATOIRE,medicamentModel.getLaboratoire());
        cv.put(PRESENTATION,medicamentModel.getPresentation());
        cv.put(C_INITIAL,medicamentModel.getC_initail());
        cv.put(C_MINIMAL,medicamentModel.getC_minimal());
        cv.put(C_MAXIMAL,medicamentModel.getC_maximal());
        cv.put(VOLUMEP,medicamentModel.getVolumep());
        cv.put(PRIX,medicamentModel.getPrix());

        long insert = db.insert(MEDICAMENTS, null, cv);
        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean addOneC(CalculModel calculModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PATIENT_NOM,calculModel.getNom());
        cv.put(PATIENT_PRENOM,calculModel.getPrenom());
        cv.put(MEDICAMENT_NOM,calculModel.getNom_medicament());
        cv.put(DOSE,calculModel.getDose());
        cv.put(VOLUME,calculModel.getVolume());
        cv.put(FLACON,calculModel.getFlacon());
        cv.put(RELIQUAT,calculModel.getReliquat());
        cv.put(RELIQUAT_FINAL,calculModel.getReliquat_final());
        cv.put(POCHE,calculModel.getPoche());

        long insert = db.insert(CALCULS, null, cv);
        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean addOneR(ReliquatModel reliquatModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MEDICAMENT_NOM,reliquatModel.getMedicament_nom());
        cv.put(RELIQUAT,reliquatModel.getReliquat());
        cv.put(MOTANT_PERDU,reliquatModel.getMontant());
        cv.put(PERIME,reliquatModel.getPreime());

        long insert = db.insert(RELIQUATS, null, cv);
        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }
    String getPatientNom(int id_p ){
        String nom = null;
        String idp = String.valueOf(id_p);
        String queryString  = "SELECT * FROM " + PATIENTS + " WHERE " + IDP + " = '" + idp + "'";
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);
            if (cursor != null){
                cursor.moveToFirst();
            }
            nom = cursor.getString(1);
        }
        if (nom == null){
            Toast.makeText(context, "Failed to get Prenom", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully got Prenom", Toast.LENGTH_SHORT).show();
        }
        return nom;

    }
    String getPatientPrenom(int id_p ){
        String prenom = null;
        String idp = String.valueOf(id_p);
        String queryString  = "SELECT * FROM " + PATIENTS + " WHERE " + IDP + " = '" + idp + "'";
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);
            if (cursor != null){
                cursor.moveToFirst();
            }
            prenom = cursor.getString(2);
        }
        if (prenom == null){
            Toast.makeText(context, "Failed to get Prenom", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully got Prenom", Toast.LENGTH_SHORT).show();
        }
        return prenom;

    }
    float getPatientSC(int id_p ){
        float sc = -1;
        String idp = String.valueOf(id_p);
        String queryString  = "SELECT * FROM " + PATIENTS + " WHERE " + IDP + " = '" + idp + "'";
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);
            if (cursor != null){
                cursor.moveToFirst();
            }
            sc = cursor.getFloat(5);
        }
        if (sc == -1){
            Toast.makeText(context, "Failed to get sc", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully got sc", Toast.LENGTH_SHORT).show();
        }
        return sc;

    }

    float getMedicamentPresentation(String nomM){
        float dose = -1;
        String queryString  = "SELECT * FROM " + MEDICAMENTS + " WHERE " + MEDICAMENT_NOM + " = '" + nomM + "'";
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);
            if (cursor != null){
                cursor.moveToFirst();
            }
            dose = cursor.getFloat(3);
        }
        if (dose == -1){
            Toast.makeText(context, "Failed to get Dose", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully got Dose", Toast.LENGTH_SHORT).show();
        }
        return dose;

    }
    float getMedicamentCI(String nomM){
        float c_initial = -1;
        String queryString  = "SELECT * FROM " + MEDICAMENTS + " WHERE " + MEDICAMENT_NOM + " = '" + nomM + "'";
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);
            if (cursor != null){
                cursor.moveToFirst();
            }
            c_initial = cursor.getFloat(4);
        }
        if (c_initial == -1){
            Toast.makeText(context, "Failed to get Dose", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully got Dose", Toast.LENGTH_SHORT).show();
        }
        return c_initial;

    }
    float getMedicamentCMin(String nomM){
        float c_minimal = -1;
        String queryString  = "SELECT * FROM " + MEDICAMENTS + " WHERE " + MEDICAMENT_NOM + " = '" + nomM + "'";
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);
            if (cursor != null){
                cursor.moveToFirst();
            }
            c_minimal = cursor.getFloat(5);
        }
        if (c_minimal == -1){
            Toast.makeText(context, "Failed to get Dose", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully got Dose", Toast.LENGTH_SHORT).show();
        }
        return c_minimal;

    }
    float getMedicamentCMax(String nomM){
        float c_maximal = -1;
        String queryString  = "SELECT * FROM " + MEDICAMENTS + " WHERE " + MEDICAMENT_NOM + " = '" + nomM + "'";
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);
            if (cursor != null){
                cursor.moveToFirst();
            }
            c_maximal = cursor.getFloat(6);
        }
        if (c_maximal == -1){
            Toast.makeText(context, "Failed to get Dose", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully got Dose", Toast.LENGTH_SHORT).show();
        }
        return c_maximal;

    }
    float getMedicamentPrix(String nomM){
        float prix = -1;
        String queryString  = "SELECT * FROM " + MEDICAMENTS + " WHERE " + MEDICAMENT_NOM + " = '" + nomM + "'";
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);
            if (cursor != null){
                cursor.moveToFirst();
            }
            prix = cursor.getFloat(8);
        }
        if (prix == -1){
            Toast.makeText(context, "Failed to get Dose", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully got Dose", Toast.LENGTH_SHORT).show();
        }
        return prix;

    }



    Cursor readAllDataP(){
        String queryString  = "SELECT * FROM " + PATIENTS;
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);

        }
        return cursor;

    }

    Cursor readAllDataM(){
        String queryString  = "SELECT * FROM " + MEDICAMENTS;
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);

        }
        return cursor;

    }
    Cursor readAllDataC(){
        String queryString  = "SELECT * FROM " + CALCULS;
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);

        }
        return cursor;

    }
    Cursor readAllDataR(){
        String queryString  = "SELECT * FROM " + RELIQUATS;
        SQLiteDatabase db  =this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);

        }
        return cursor;

    }

    void updateDataPatient(String row_id, String nom, String prenom, String taille, String poids, float sc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PATIENT_NOM,nom);
        cv.put(PATIENT_PRENOM,prenom);
        cv.put(TAILLE,taille);
        cv.put(POIDS,poids);
        cv.put(SURFACCE_CORPORELLE,sc);

        long result = db.update(PATIENTS, cv, "IDP=?", new String[]{row_id});

        if (result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    void updateDataMedicament(String row_id, String nom, String labo, String presentation, String ci, String cmin, String cmax, String prix){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MEDICAMENT_NOM,nom);
        cv.put(LABORATOIRE,labo);
        cv.put(PRESENTATION,presentation);
        cv.put(C_INITIAL,ci);
        cv.put(C_MINIMAL,cmin);
        cv.put(C_MAXIMAL,cmax);
        cv.put(PRIX,prix);

        long result = db.update(MEDICAMENTS, cv, "IDM=?", new String[]{row_id});

        if (result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    void deletePatient(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(PATIENTS,"IDP=?",new String[]{row_id});

    }

    void deleteMedicament(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(MEDICAMENTS,"IDM=?",new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }





}
