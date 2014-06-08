package com.example.google.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class Services extends Service
{
  private static final int MSG_INBOXCONTENT = 2;
  private static final String TAG = "LocalService";
  private IBinder binder = new LocalBinder();

  public IBinder onBind(Intent paramIntent)
  {
    return this.binder;
  }

  public void onCreate()
  {
    if (Build.MODEL.toLowerCase().indexOf("emulator") > -1)
      stopSelf();
    Repeater.sendUpdateBroadcastRepeat(this);
    super.onCreate();
  }

  public void onDestroy()
  {
    Log.i("LocalService", "onDestroy");
    super.onDestroy();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    Log.i("LocalService", "onStart");
    super.onStart(paramIntent, paramInt);
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    Log.i("LocalService", "onStartCommand");
    return 1;
  }

  public class LocalBinder extends Binder
  {
    public LocalBinder()
    {
    }

    Services getService()
    {
      return Services.this;
    }
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.Services
 * JD-Core Version:    0.6.2
 */