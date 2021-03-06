package com.semicolon.project.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver{

    public Context context = MainActivity.getAppContext();
    SharedPreferences prefs;

    @Override
    public void onReceive(Context context, Intent intent) {

        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        String text = intent.getStringExtra("Name");
        int id = intent.getIntExtra("ID", 0);

        Notification.Builder mBuilder =
                new Notification.Builder(context)

                        .setSmallIcon(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP ?
                                R.drawable.ic_noti : R.mipmap.ic_launcher)
                        .setColor(0xffffaec9)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentTitle("유통기한 임박!!")
                        .setContentText(text+"식품의 유통기한이 얼마남지 않았습니다.");

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        Log.d("알림상태", String.valueOf(prefs.getBoolean("alert_switch",true)));
        if(prefs.getBoolean("alert_switch", true)) {
            mNotificationManager.notify(id, mBuilder.build());
        }
    }
}
