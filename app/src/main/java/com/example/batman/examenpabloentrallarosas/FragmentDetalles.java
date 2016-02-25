package com.example.batman.examenpabloentrallarosas;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentDetalles.Listener} interface
 * to handle interaction events.
 * Use the {@link FragmentDetalles#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetalles extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "concesionario";

    private Concesionario concesionario;
    private EditText marca;
    private EditText modelo;
    private EditText potencia;
    private EditText precio;
    private Button aceptar;
    private Listener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param concesionario, una clase donde están todos los coches
     * @return A new instance of fragment Detalles.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDetalles newInstance(Concesionario concesionario) {
        FragmentDetalles fragment = new FragmentDetalles();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, concesionario);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentDetalles() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            concesionario = (Concesionario) getArguments().getSerializable(ARG_PARAM1);
        }
        if (savedInstanceState != null) {
            concesionario = (Concesionario) savedInstanceState.getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalles, container, false);
        aceptar = (Button) v.findViewById(R.id.botonMas1);
        aceptar.setOnClickListener(this);
        marca = (EditText) v.findViewById(R.id.marca);
        modelo = (EditText) v.findViewById(R.id.modelo);
        potencia = (EditText) v.findViewById(R.id.potencia);
        precio = (EditText) v.findViewById(R.id.precio);
        if (concesionario.getSelected() > -1) {
            // Tenemos algo seleccionado, solo podemos mirar, no tocar
            Coche coche = concesionario.getCoches().get(concesionario.getSelected());
            marca.setText(coche.getMarca());
            modelo.setText(coche.getModelo());
            potencia.setText(coche.getPotencia());
            precio.setText(coche.getPrecio());
            aceptar.setVisibility(View.INVISIBLE);
        } else {
            aceptar.setVisibility(View.VISIBLE);
        }
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_PARAM1, concesionario);
    }

    @Override
    public void onClick(View v) {
        // ACEPTAR
        if (mListener != null) {
            // validamos
            boolean ok = true;
            String auxMarca = marca.getText().toString();
            String auxModelo = modelo.getText().toString();
            String auxPotencia = potencia.getText().toString();
            String auxPrecio = precio.getText().toString();
            if (auxMarca.equals("")) ok = false;
            else if (auxModelo.equals("")) ok = false;
            else if (auxPotencia.equals("")) ok = false;
            else if (auxPrecio.equals("")) ok = false;

            if (ok) {
                concesionario.addCoche(new Coche(auxMarca, auxModelo, auxPotencia, auxPrecio));
                mListener.volverMenu();
            } else {
                mListener.tostada("No se ha insertado el coche: campos en blanco");
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface Listener {
        public void tostada(String mensaje);
        public void volverMenu();
    }

}
