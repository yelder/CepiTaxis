package com.cepibase.cepitaxis;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {
    static ArrayList<Taxi> taxisDisponibles;
    int CODIGO_PERMISOS= 1;
    String[] Permisos={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    static AsistenteSQLite asistenteSQLite;
    Button btnLocalizar,btnHistorial;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        if (!tienePermisos(Permisos)){
            ActivityCompat.requestPermissions(PrincipalActivity.this,Permisos, CODIGO_PERMISOS);
        }else{
            TareaXMLServidor tareaXMLServidor=new
                    TareaXMLServidor("http://desarrollo.cepibase.es/taxis/disponibles.php");
            tareaXMLServidor.execute();
            asistenteSQLite= new AsistenteSQLite(getApplicationContext(), "cepitaxis.db", null, 1);
        }
        btnHistorial=findViewById(R.id.btnHistorial);
        btnLocalizar=findViewById(R.id.btnLocalizar);
        btnLocalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PrincipalActivity.this,LocalizarActivity.class);
                startActivity(i);
            }
        });
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PrincipalActivity.this,HistorialActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            TareaXMLServidor tareaXMLServidor=new TareaXMLServidor("http://desarrollo.cepibase.es/taxis/disponibles.php");
            tareaXMLServidor.execute();
            asistenteSQLite= new AsistenteSQLite(getApplicationContext(), "cepitaxis.db", null, 1);
        }else {
            ActivityCompat.requestPermissions(PrincipalActivity.this,Permisos,CODIGO_PERMISOS);
        }
    }

    private boolean tienePermisos(String[]permisos){
        if (this != null && permisos !=null){
            for (String permiso:permisos){
                if (ActivityCompat.checkSelfPermission(this,permiso)!=
                        PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }
}
