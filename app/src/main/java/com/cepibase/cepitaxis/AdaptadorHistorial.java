package com.cepibase.cepitaxis;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorHistorial extends BaseAdapter {
    Activity actividad;
    ArrayList<String[]> listado;

    public AdaptadorHistorial(Activity actividad, ArrayList<String[]> listado) {
        this.actividad = actividad;
        this.listado = listado;
    }

    @Override
    public int getCount() {
        return listado.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = actividad.getLayoutInflater();
        View vista = layoutInflater.inflate(R.layout.elemento_lista, null, true);

        TextView txtMatricula, txtFecha, txtNombre, txtApellidos;
        txtMatricula = vista.findViewById(R.id.txtMatricula);
        txtFecha = vista.findViewById(R.id.txtFecha);
        txtNombre = vista.findViewById(R.id.txtNombre);
        txtApellidos = vista.findViewById(R.id.txtApellidos);

        txtMatricula.setText(listado.get(position)[0]);
        txtFecha.setText(listado.get(position)[1]);
        txtNombre.setText(listado.get(position)[2]);
        txtApellidos.setText(listado.get(position)[3]);
        return vista;
    }
}