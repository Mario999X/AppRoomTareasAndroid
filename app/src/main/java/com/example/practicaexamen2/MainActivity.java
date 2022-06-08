package com.example.practicaexamen2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    El Proyecto exigia que la APP se encontrara en fragmentos, por lo que la Main Activity sirve
    unicamente de soporte para el fragmento. La imagen principal (+) se usara como boton para
    agregar tareas, su uso se encuentra en la clase FragmentoLista, así como el dialogo que
    permite seleccionar las caracteristicas de la tarea.

    La BBDD ha sido creada como siempre y en el mismo orden.

    Lo hecho con el datePicker no es lo mas optimo, pero funciona, asi como lo hecho con el spinner.

    Pulsando sobre la imagen de prioridad de cada elemento de la lista, elimina la tarea en cuestion.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reemplazarFragmentos(new FragmentoLista());
    }

    private void reemplazarFragmentos(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragLayout, f);
        ft.commit();
    }

}