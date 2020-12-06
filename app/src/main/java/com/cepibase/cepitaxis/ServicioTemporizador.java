package com.cepibase.cepitaxis;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ServicioTemporizador extends Service {

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        final Intent i = intent;
        int tiempoEspera = i.getIntExtra("tiempoEspera", 0);
        CountDownTimer temporizador = new CountDownTimer(tiempoEspera * 6, tiempoEspera * 6) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                getApplicationContext().stopService(i);
                lanzarNotificacion();
                Intent i = new Intent(ServicioTemporizador.this, HistorialActivity.class);
                startActivity(i);
            }
        };
        temporizador.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void lanzarNotificacion() {
        String idCanal = "Notificaciones";
        String nombreCanal = "Cepi Taxis";
        int importanciaCanal = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel notificationChannel = new NotificationChannel(idCanal, nombreCanal, importanciaCanal);
        notificationChannel.enableVibration(true);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), idCanal).
                setSmallIcon(R.drawable.icono_taxi_mini).
                setChannelId(idCanal).setContentText("El taxi solicitado le espera en su ubicacion actual");
        notificationManager.notify(1, builder.build());
    }
}