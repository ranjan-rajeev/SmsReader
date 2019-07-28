package com.horizonlabs.smsreader.data;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.horizonlabs.smsreader.data.local.dao.SMSDao;
import com.horizonlabs.smsreader.data.local.dao.SMSKEYDao;
import com.horizonlabs.smsreader.data.local.dao.UserDao;
import com.horizonlabs.smsreader.data.local.model.SMSEntity;
import com.horizonlabs.smsreader.data.local.model.SMSKeyWord;
import com.horizonlabs.smsreader.data.local.model.UserEntity;
import com.horizonlabs.smsreader.utility.Utility;

import java.util.concurrent.Executors;

/**
 * Created by Rajeev Ranjan -  ABPB on 27-07-2019.
 */
@Database(entities = {SMSEntity.class, SMSKeyWord.class, UserEntity.class}, version = 1, exportSchema = false)
public abstract class SmsReaderDatabase extends RoomDatabase {

    private Callback callback;
    private static SmsReaderDatabase smsReaderDatabase;

    public abstract SMSDao smsDao();

    public abstract SMSKEYDao smskeyDao();

    public abstract UserDao userDao();

    public static synchronized SmsReaderDatabase getInstance(final Context context) {
        if (smsReaderDatabase == null) {
            smsReaderDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    SmsReaderDatabase.class, "sms_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    getInstance(context).userDao().saveUser(UserEntity.populateData());
                                    getInstance(context).smskeyDao().saveKey(SMSKeyWord.populateData());
                                    getInstance(context).smsDao().saveSms(SMSEntity.populateData());
                                }
                            });
                        }
                    })
                    .build();
        }
        return smsReaderDatabase;
    }

    private Callback getCallback(final Context context) {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        getInstance(context).userDao().saveUser(Utility.populateData());
                        getInstance(context).smskeyDao().saveKey(Utility.getKeyWork());
                    }
                });
            }
        };
    }
}
