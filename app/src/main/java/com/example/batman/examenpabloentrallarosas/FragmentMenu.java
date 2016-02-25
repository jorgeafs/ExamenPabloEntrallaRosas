package com.example.batman.examenpabloentrallarosas;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMenu.Listener} interface
 * to handle interaction events.
 * Use the {@link FragmentMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMenu extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "concesionario";

    // TODO: Rename and change types of parameters
    private Concesionario concesionario;
    Button pon;
    Button mira;
    Spinner spin;

    private Listener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param concesionario, una clase donde están todos los coches
     * @return A new instance of fragment FragmentMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMenu newInstance(Concesionario concesionario) {
        FragmentMenu fragment = new FragmentMenu();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, concesionario);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentMenu() {
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
        View v = inflater.inflate(R.layout.fragment_fragment_menu, container, false);
        Context contexto = getContext();
        // referenciamos los controles
        pon = (Button) v.findViewById(R.id.botonPon);
        mira = (Button) v.findViewById(R.id.botonMira);
        spin = (Spinner) v.findViewById(R.id.spinner);
        ArrayList<Coche> lista = concesionario.getCoches();
        lista.add(0, new Coche("Selecciona uno", "", "", ""));
        spin.setAdapter(new CocheAdapter(contexto, concesionario.getCoches()));
        // Ponemos los eventos
        pon.setOnClickListener(this);
        mira.setOnClickListener(this);

        spin.setSelected(false);
        spin.setOnItemSelectedListener(this);

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
        if (mListener != null) {
            if (v instanceof Button) {
                Button pulsado = (Button) v;
                if (pulsado.equals(pon)) {
                    mListener.tostada("Pongo cosas");
                    spin.setVisibility(View.INVISIBLE);
                    concesionario.setSelected(-1);
                    mListener.mostrarDetalles();
                    // main haz swap
                }

                if (pulsado.equals(mira)) {
                    mListener.tostada("Miro cosas");
                    spin.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (mListener != null) {
            if (position != 0) {
                mListener.tostada("Estoy en la posicion " + position);
                concesionario.setSelected(position);
                mListener.mostrarDetalles();
                // Main haz swap
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
        public void mostrarDetalles();
    }

}
