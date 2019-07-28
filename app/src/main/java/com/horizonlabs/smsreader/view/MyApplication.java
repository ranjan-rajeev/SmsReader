package com.horizonlabs.smsreader.view;

import android.app.Application;
import android.content.IntentFilter;
import android.provider.Telephony;

import com.horizonlabs.smsreader.service.SmsReceiver;

public class MyApplication extends Application {
    SmsReceiver smsReceiver;
    @Override
    public void onCreate() {
        super.onCreate();
        smsReceiver = new SmsReceiver();
        registerReceiver(smsReceiver,new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterReceiver(smsReceiver);
    }
}
