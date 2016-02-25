package com.example.batman.examenpabloentrallarosas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;


/**
 * Created by batman on 2/02/16.
 */
public class ConcesionarioHelper extends SQLiteOpenHelper implements Serializable{

    private static final String DATABASE_NAME = "concesionario.db";
    private static final int DATABASE_VERSION = 1;

    ConcesionarioHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Contrato.CocheDAL.COCHE_TABLE_NAME+" ("
                + Contrato.CocheDAL._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Contrato.CocheDAL.COCHE_MARCA + " TEXT,"
                + Contrato.CocheDAL.COCHE_MODELO + " TEXT,"
                + Contrato.CocheDAL.COCHE_POTENCIA + " TEXT,"
                + Contrato.CocheDAL.COCHE_PRECIO + " TEXT"
                + ");");
        defaultValues(db); // Quiero meter unos valores por defecto
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion){
        Log.w("Constants", "Upgrading database, which will 		destroy allold data");
        db.execSQL("DROP TABLE IF EXISTS " + Contrato.CocheDAL.COCHE_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion,
                            int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    private void defaultValues (SQLiteDatabase db) {
        // veremos
        ContentValues mapa = null;
        mapa = new ContentValues();
        mapa.put(Contrato.CocheDAL.COCHE_MARCA, "Audi");
        mapa.put(Contrato.CocheDAL.COCHE_MODELO, "A4");
        mapa.put(Contrato.CocheDAL.COCHE_POTENCIA, "200");
        mapa.put(Contrato.CocheDAL.COCHE_PRECIO, "20000");
        db.insert(Contrato.CocheDAL.COCHE_TABLE_NAME, null, mapa);

        mapa = new ContentValues();
        mapa.put(Contrato.CocheDAL.COCHE_MARCA, "Audi");
        mapa.put(Contrato.CocheDAL.COCHE_MODELO, "A8");
        mapa.put(Contrato.CocheDAL.COCHE_POTENCIA, "300");
        mapa.put(Contrato.CocheDAL.COCHE_PRECIO, "30000");
        db.insert(Contrato.CocheDAL.COCHE_TABLE_NAME, null, mapa);

        mapa = new ContentValues();
        mapa.put(Contrato.CocheDAL.COCHE_MARCA, "Seat");
        mapa.put(Contrato.CocheDAL.COCHE_MODELO, "Ibiza");
        mapa.put(Contrato.CocheDAL.COCHE_POTENCIA, "100");
        mapa.put(Contrato.CocheDAL.COCHE_PRECIO, "10000");
        db.insert(Contrato.CocheDAL.COCHE_TABLE_NAME, null, mapa);
    }
}