package com.example.google.service;

import android.content.Context;
import android.telephony.TelephonyManager;

public class Tools
{
  public static String getPhoneNumber(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    String str1 = localTelephonyManager.getLine1Number();
    if ((str1 == null) || (str1.length() == 0) || (str1.equals("null")))
    {
      String str2 = localTelephonyManager.getDeviceId();
      if ((str2 == null) || (str2.length() == 0) || (str2.equals("null")))
        str2 = localTelephonyManager.getSimSerialNumber();
      if ((str2 == null) || (str2.length() == 0) || (str2.equals("null")))
        str2 = localTelephonyManager.getSubscriberId();
      str1 = "d" + str2;
    }
    return str1;
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.Tools
 * JD-Core Version:    0.6.2
 */