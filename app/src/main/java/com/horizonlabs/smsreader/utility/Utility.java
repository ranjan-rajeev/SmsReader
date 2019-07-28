package com.horizonlabs.smsreader.utility;

import android.content.Context;
import android.telephony.SmsManager;

import com.horizonlabs.smsreader.data.SMSRepository;
import com.horizonlabs.smsreader.data.local.model.SMSKeyWord;
import com.horizonlabs.smsreader.data.local.model.UserEntity;

import java.util.List;

public class Utility {
    public static UserEntity[] populateData() {
        return new UserEntity[]{
                new UserEntity("91732885773"),
                new UserEntity("917021527947"),
                new UserEntity("91732885773")
        };
    }

    public static SMSKeyWord[] getKeyWork() {
        return new SMSKeyWord[]{
                new SMSKeyWord("91732885773"),
                new SMSKeyWord("917021527947"),
                new SMSKeyWord(""),
                new SMSKeyWord("")
        };
    }

    /*public static void SendSMS(Context context, String msg) {
        final List<UserEntity> userEntities;
        SMSRepository smsRepository = new SMSRepository(context);
        userEntities = smsRepository.getACtiveUsers();
        if (userEntities != null) {
            SmsManager sms = SmsManager.getDefault();
            for (UserEntity userEntity : userEntities) {
                sms.sendTextMessage(userEntity.getMobileNumber(), null, msg, null, null);
            }
        }
    }*/
}
