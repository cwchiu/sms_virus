package com.example.google.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SMSServiceBootReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.example.google.service.Services");
    paramContext.startService(localIntent);
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.SMSServiceBootReceiver
 * JD-Core Version:    0.6.2
 */