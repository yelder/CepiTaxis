package com.cepibase.cepitaxis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {
    Button btnLogin, btnRegistro;
    static SharedPreferences preferencias;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_inicial);
        btnLogin=findViewById(R.id.btnLogin);
        btnRegistro=findViewById(R.id.btnRegistro);
        leerPreferencias();
        iniciarAnimacionLogo();
        iniciarAnimacionPanel();
        iniciarVideo();
    }
    void leerPreferencias(){
        preferencias= PreferenceManager.getDefaultSharedPreferences(this);
        if (!preferencias.contains("nickname")){
            btnLogin.setVisibility(View.GONE);
        }else{
            btnRegistro.setVisibility(View.GONE);
            btnLogin.setText(btnLogin.getText()+" "+preferencias.getString("nickname",""));
        }
    }
    public void opcionLogin(View v){
        Intent i=new Intent(InicioActivity.this,PrincipalActivity.class);
        startActivity(i);
    }
    public void opcionRegistro(View v){
        Intent i=new Intent(InicioActivity.this,PreferenciaRegistro.class);
        startActivity(i);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
    void iniciarAnimacionLogo(){
        ImageView logo=findViewById(R.id.imgLogo);
        Animation muestra_logo= AnimationUtils.loadAnimation(this,R.anim.animacion_logo);
        logo.startAnimation(muestra_logo);
    }
    void iniciarAnimacionPanel(){
        LinearLayout panelBotones=findViewById(R.id.panelBotones);
        Animation muestra_botones=AnimationUtils.loadAnimation(this,R.anim.animacion_botones);
        panelBotones.startAnimation(muestra_botones);
    }
    private void iniciarVideo(){
        VideoView videoView=findViewById(R.id.videoView);
        Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.taxi_fondo);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
    }
}
