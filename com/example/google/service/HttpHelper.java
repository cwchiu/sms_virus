package com.example.google.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpHelper
{
  protected Context _Context = null;

  public HttpHelper(Context paramContext)
  {
    this._Context = paramContext;
  }

  private String callWS(String paramString)
    throws ParserConfigurationException, UnsupportedEncodingException
  {
    HttpGet localHttpGet = new HttpGet(paramString);
    try
    {
      HttpResponse localHttpResponse = new DefaultHttpClient().execute(localHttpGet);
      Object localObject = "";
      if (localHttpResponse.getStatusLine().getStatusCode() == 200)
      {
        String str = EntityUtils.toString(localHttpResponse.getEntity());
        localObject = str;
      }
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  protected void callWSSub(final Handler paramHandler, final int paramInt, final String paramString)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          String str = HttpHelper.this.callWS(paramString);
          if ((str.length() > 0) && (paramHandler != null))
          {
            Message localMessage = new Message();
            localMessage.what = paramInt;
            localMessage.obj = str;
            paramHandler.sendMessage(localMessage);
          }
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
  }

  protected void callWSSub(Handler paramHandler, String paramString)
  {
    callWSSub(paramHandler, -1, paramString);
  }

  protected void callWSSub(String paramString)
  {
    callWSSub(null, -1, paramString);
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.HttpHelper
 * JD-Core Version:    0.6.2
 */