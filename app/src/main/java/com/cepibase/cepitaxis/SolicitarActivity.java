package com.cepibase.cepitaxis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SolicitarActivity extends AppCompatActivity {
    SharedPreferences preferencias;
    TextView txtNombre, txtApellidos, txtMatricula, txtMinutos;
    Button btnConfirmar;
    String matricula, nombre, apellidos;
    int minutos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar);
        preferencias= PreferenceManager.getDefaultSharedPreferences(this);
        nombre=preferencias.getString("nombre","");
        apellidos=preferencias.getString("apellidos", "");
        matricula=this.getIntent().getExtras().getString("matricula");
        minutos=9;
        txtNombre=findViewById(R.id.txtNombre);
        txtApellidos=findViewById(R.id.ConstraintLayout);
        txtMatricula=findViewById(R.id.txtMatricula);
        txtMinutos=findViewById(R.id.txtMinutos);
        txtNombre.setText(nombre);
        txtApellidos.setText(apellidos);
        txtMatricula.setText(matricula);
        txtMinutos.setText(minutos+"");

        btnConfirmar=findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrincipalActivity.asistenteSQLite.nuevoRegistro(matricula,nombre,apellidos);
                setContentView(R.layout.activity_en_curso);
                Intent intent=new Intent(SolicitarActivity.this,ServicioTemporizador.class);
                intent.putExtra("tiempoespera",minutos);
                startService(intent);
            }
        });
    }
}
