package com.example.batman.examenpabloentrallarosas;

import android.content.Context;
import android.database.Cursor;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by batman on 4/02/16.
 */
public class Concesionario implements Serializable{
    private int selected;
    private ArrayList<Coche> coches;
    private DAL bbdd;

    public Concesionario (Context contexto) {
        bbdd = new DAL (contexto);
        selected = -1;
        refresh();
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public void refresh () {
        coches = selectCoches(bbdd.getCursor());
    }

    public ArrayList<Coche> selectCoches (Cursor cursor) {
        ArrayList<Coche> resultado = new ArrayList<Coche>();

        if (cursor.moveToFirst()) {
            do {
                String marca = cursor.getString(cursor.getColumnIndex(Contrato.CocheDAL.COCHE_MARCA));
                String modelo = cursor.getString(cursor.getColumnIndex(Contrato.CocheDAL.COCHE_MODELO));
                String potencia = cursor.getString(cursor.getColumnIndex(Contrato.CocheDAL.COCHE_POTENCIA));
                String precio = cursor.getString(cursor.getColumnIndex(Contrato.CocheDAL.COCHE_PRECIO));
                resultado.add(new Coche(marca, modelo, potencia, precio));
            }while (cursor.moveToNext());
        }

        cursor.close();
        return resultado;
    }

    public ArrayList<Coche> addCoche (Coche nuevo) {
        coches = selectCoches(bbdd.addCoche(nuevo));
        return coches;
    }

    public ArrayList<Coche> getCoches () {
        return coches;
    }
}
