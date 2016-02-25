package com.example.batman.examenpabloentrallarosas;

import android.provider.BaseColumns;


/**
 * Created by batman on 4/02/16.
 */
public class Contrato {
    private Contrato () {}

    public static final class CocheDAL implements BaseColumns {
        private CocheDAL () {}

        public static final String COCHE_TABLE_NAME = "table_coche";
        public static final String COCHE_MARCA = "coche_marca";
        public static final String COCHE_MODELO = "coche_modelo";
        public static final String COCHE_POTENCIA = "coche_potencia";
        public static final String COCHE_PRECIO = "coche_precio";
        public static final String DEFAULT_SORT_ORDER ="coche_marca ASC";

    }
}
