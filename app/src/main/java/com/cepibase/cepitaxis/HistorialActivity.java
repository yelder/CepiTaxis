package com.cepibase.cepitaxis;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistorialActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        ListView lista = findViewById(R.id.list);
        ArrayList<String[]> listadoHistorial = PrincipalActivity.asistenteSQLite.obtenerHistorial();
        AdaptadorHistorial adaptadorHistorial = new AdaptadorHistorial(this, listadoHistorial);
        lista.setAdapter(adaptadorHistorial);
    }
}
