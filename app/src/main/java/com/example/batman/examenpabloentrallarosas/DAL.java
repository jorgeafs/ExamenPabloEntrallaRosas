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
public class DAL implements Serializable {
    private Context context;
    private ConcesionarioHelper helper;

    DAL (Context context) {
        this.context = context;
        this.helper = new ConcesionarioHelper(context);
    }

    // Devuelve un cursor con todos los coches de la bbdd
    public Cursor getCursor () {
        Cursor resultado = null;
        String [] columnas = {Contrato.CocheDAL.COCHE_MARCA, Contrato.CocheDAL.COCHE_MODELO, Contrato.CocheDAL.COCHE_POTENCIA, Contrato.CocheDAL.COCHE_PRECIO};
        resultado = helper.getReadableDatabase().query(
                // Nombre de la tabla
                Contrato.CocheDAL.COCHE_TABLE_NAME,
                // Columnas
                columnas,
                // Where
                null,
                // Argumentos del where
                null,
                // Group By
                null,
                // Having
                null,
                // Order by
                Contrato.CocheDAL.DEFAULT_SORT_ORDER
        );
        return resultado;
    }

    public Cursor addCoche (Coche nuevo) {
        ContentValues mapa = new ContentValues();
        mapa.put(Contrato.CocheDAL.COCHE_MARCA, nuevo.getMarca());
        mapa.put(Contrato.CocheDAL.COCHE_MODELO, nuevo.getModelo());
        mapa.put(Contrato.CocheDAL.COCHE_POTENCIA, nuevo.getPotencia());
        mapa.put(Contrato.CocheDAL.COCHE_PRECIO, nuevo.getPrecio());
        helper.getWritableDatabase().insert(Contrato.CocheDAL.COCHE_TABLE_NAME, null, mapa);
        return getCursor();
    }

}
