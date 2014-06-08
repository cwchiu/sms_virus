package com.example.google.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class Repeater
{
  public static void sendUpdateBroadcastRepeat(Context paramContext)
  {
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, 0, new Intent(paramContext, TaskRequest.class), 0);
    long l = SystemClock.elapsedRealtime();
    ((AlarmManager)paramContext.getSystemService("alarm")).setRepeating(2, l, 40000L, localPendingIntent);
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.Repeater
 * JD-Core Version:    0.6.2
 */