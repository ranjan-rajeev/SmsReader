package com.horizonlabs.smsreader.service;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;

import com.horizonlabs.smsreader.data.SMSRepository;
import com.horizonlabs.smsreader.data.SmsReaderDatabase;
import com.horizonlabs.smsreader.data.local.dao.SMSKEYDao;
import com.horizonlabs.smsreader.data.local.dao.UserDao;
import com.horizonlabs.smsreader.data.local.model.UserEntity;
import com.horizonlabs.smsreader.utility.Utility;
import com.horizonlabs.smsreader.view.MainActivity;

import java.util.List;

import static java.net.Proxy.Type.HTTP;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {







            sendSMS(context,"9372885773","Welcome ",getSubscriptionId(context));
            // Retrieves a map of extended data from the intent.
            /*final Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String str = "";

            try {
                if (bundle != null) {
                    //---retrieve the SMS message received---
                    Object[] pdus = (Object[]) bundle.get("pdus");

                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        str += "SMS from " + msgs[i].getOriginatingAddress();
                        str += " :";
                        str += msgs[i].getMessageBody().toString();
                        str += "\n";
                    }
                    String replyPhone = msgs[0].getOriginatingAddress();
                    String request = msgs[0].getMessageBody().toString();

                    new SendSMSAsyncTask(context, replyPhone, request).execute();
                }
            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" + e);

            }*/
        }
    }

    private class SendSMSAsyncTask extends AsyncTask<Void, Void, Void> {
        private SMSKEYDao smskeyDao;
        private UserDao userDao;
        List<UserEntity> numbers;
        private Context context;
        String from, msg;

        private SendSMSAsyncTask(Context context, String from, String msg) {
            this.context = context;
            this.from = from;
            this.msg = msg;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            smskeyDao = SmsReaderDatabase.getInstance(context).smskeyDao();
            userDao = SmsReaderDatabase.getInstance(context).userDao();
            if (smskeyDao.getKey(from) != null) {
                numbers = userDao.getActiveUser();
                if (numbers != null) {
                    SmsManager sms = SmsManager.getDefault();
                    for (UserEntity userEntity : numbers) {
                        //composeMmsMessage(context, msg, userEntity.getMobileNumber());
                        sendSMS(context, userEntity.getMobileNumber(), msg, getSubscriptionId(context));
                        //sms.sendTextMessage(userEntity.getMobileNumber(), null, msg, null, null);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void b) {
            super.onPostExecute(b);
        }
    }

    public void composeMmsMessage(Context context, String message, String number) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private int getSubscriptionId(Context context) {
        int subs = 0;
        SubscriptionManager subscriptionManager = null;
        subscriptionManager = SubscriptionManager.from(context.getApplicationContext());
        List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
        subs = subscriptionManager.getDefaultDataSubscriptionId();

        return subs;
    }

    private void sendSMS(Context context, String phoneNumber, String message, int subscriptionId) {

        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,
                new Intent(context, SmsSentReceiver.class), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0,
                new Intent(context, SmsDeliveredReceiver.class), 0);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1 && subscriptionId != 0) {
            SmsManager.getSmsManagerForSubscriptionId(subscriptionId).
                    sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
        } else {
            SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
        }

    }
}