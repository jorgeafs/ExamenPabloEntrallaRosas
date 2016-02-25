package com.example.batman.examenpabloentrallarosas;

import android.database.Cursor;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentDetalles.Listener, FragmentMenu.Listener {

    FragmentDetalles fragmentDetalles;
    FragmentMenu fragmentMenu;
    Concesionario concesionario;
    boolean menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        if (savedInstanceState != null) {
            concesionario = (Concesionario) savedInstanceState.getSerializable("concesionario");
            menu = savedInstanceState.getBoolean("menu");
        } else {
            concesionario = new Concesionario(this);
            menu = true;
        }

        // FRAGMENTS!

        fragmentMenu = null;
        fragmentDetalles = null;

        if (menu) {
            // mostramos el menu
            fragmentMenu = FragmentMenu.newInstance(concesionario);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameSolo, fragmentMenu);
            transaction.commit();
        } else {
            // mostramos detalles
            fragmentDetalles = FragmentDetalles.newInstance(concesionario);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameSolo, fragmentDetalles);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("concesionario", concesionario);
        outState.putBoolean("menu", menu);
    }

    @Override
    public void tostada(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void mostrarDetalles () {
        menu = false;
        fragmentDetalles = FragmentDetalles.newInstance(concesionario);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameSolo, fragmentDetalles);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void volverMenu () {
        // No añade a BackStack a proposito, para que si estamos en el menú y le damos a back, nos saque de la app
        menu = true;
        fragmentMenu = FragmentMenu.newInstance(concesionario);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameSolo, fragmentMenu);
        transaction.commit();
    }
}
