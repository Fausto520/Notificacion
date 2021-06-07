package com.fausto.notificacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button btn_notificame;
private static  final String PRIMARY_CHANNEL_ID ="primary_notification_channel";
private static final int NOTIFICATION_ID = 0;
private NotificationManager mNotifyManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_notificame = findViewById(R.id.btn_notificacion);

        btn_notificame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotificacion();
            }
        });

         createNotificationChannel();

    }

    public void sendNotificacion(){
      NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
      mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }

    public void createNotificationChannel(){
        mNotifyManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificaionChannel = new NotificationChannel (PRIMARY_CHANNEL_ID, "Mascot Notification", NotificationManager.IMPORTANCE_HIGH);
            notificaionChannel.enableLights(true);
            notificaionChannel.setLightColor(Color .GREEN);
            notificaionChannel.enableVibration(true);
            notificaionChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificaionChannel);
        }
    }


    private NotificationCompat.Builder getNotificationBuilder(){

        Intent notificatinintent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, notificatinintent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Tienes una notificacion")
                .setContentText("Esta es tu notificacion")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);

        return  notifyBuilder;


    }
}