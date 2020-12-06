package com.cepibase.cepitaxis;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocalizarActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    LatLng ubicacionBCN = new LatLng(41.392842, 2.158265);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizar);
        SupportMapFragment fragmento = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        fragmento.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnInfoWindowClickListener(this);
        CameraPosition nuevaPosicion = new CameraPosition.Builder().target(ubicacionBCN).zoom(16).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(nuevaPosicion));

        try {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        }catch (SecurityException e){
            e.printStackTrace();
        }

        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icono_taxi_mini);
        for(Taxi taxi: PrincipalActivity.taxisDisponibles){
            if(taxi.isDisponible()){
                MarkerOptions marcadorTaxi = new MarkerOptions().position(new LatLng(taxi.getLat(), taxi.getLng())).title(taxi.getMatricula()).
                        icon(icon).snippet("Presiona para seleccionar este taxi");
                googleMap.addMarker(marcadorTaxi);
            }
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent i = new Intent(LocalizarActivity.this, SolicitarActivity.class);
        i.putExtra("matricula", marker.getTitle());
        startActivity(i);
    }
}
