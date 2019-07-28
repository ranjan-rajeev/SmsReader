package com.horizonlabs.smsreader.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.horizonlabs.smsreader.data.local.dao.SMSDao;
import com.horizonlabs.smsreader.data.local.dao.SMSKEYDao;
import com.horizonlabs.smsreader.data.local.dao.UserDao;
import com.horizonlabs.smsreader.data.local.model.SMSEntity;
import com.horizonlabs.smsreader.data.local.model.SMSKeyWord;
import com.horizonlabs.smsreader.data.local.model.UserEntity;

import java.util.List;

public class SMSRepository {

    private SMSDao smsDao;
    private SMSKEYDao smskeyDao;
    private UserDao userDao;
    LiveData<List<UserEntity>> userEntities;
    LiveData<List<SMSKeyWord>> smsKeyWords;
    LiveData<List<SMSEntity>> smsEntities;
    List<UserEntity> activeUsers;
    SMSKeyWord smsKeyWord;

    public SMSRepository(Context context) {
        smsDao = SmsReaderDatabase.getInstance(context).smsDao();
        smskeyDao = SmsReaderDatabase.getInstance(context).smskeyDao();
        userDao = SmsReaderDatabase.getInstance(context).userDao();
        userEntities = userDao.getAllUser();
    }

    public void insertUser(UserEntity... userEntities) {
        new InsertUserAsyncTask(userDao).execute(userEntities);
    }

    public void deleteUser(UserEntity... userEntities) {
        new DeleteUserAsyncTask(userDao).execute(userEntities);
    }

    public void insertKey(SMSKeyWord... userEntities) {
        new InsertKeyAsyncTask(smskeyDao).execute(userEntities);
    }

    public void insertSMS(SMSEntity... userEntities) {
        new InsertSMSAsyncTask(smsDao).execute(userEntities);
    }

    public LiveData<List<SMSKeyWord>> getAllKey() {
        if (smsKeyWords == null)
            return smskeyDao.getAllKeys();
        return smsKeyWords;
    }

    /*public boolean getKey(String key) {
        new GetKeyAsyncTask(smskeyDao, key).execute();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (smsKeyWord == null)
            return false;
        else
            return true;
    }

    public List<UserEntity> getACtiveUsers() {
        if (activeUsers != null) {
            new GetActiveUsers(userDao).execute();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return activeUsers;
    }*/

    public LiveData<List<UserEntity>> getAllUsers() {
        if (userEntities == null) {
            return userDao.getAllUser();
        }
        return userEntities;
    }

    private static class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {

        private UserDao scanDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.scanDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... scanEntities) {
            scanDao.saveUser(scanEntities);
            return null;
        }
    }

    private static class InsertKeyAsyncTask extends AsyncTask<SMSKeyWord, Void, Void> {

        private SMSKEYDao scanDao;

        private InsertKeyAsyncTask(SMSKEYDao userDao) {
            this.scanDao = userDao;
        }

        @Override
        protected Void doInBackground(SMSKeyWord... scanEntities) {
            scanDao.saveKey(scanEntities);
            return null;
        }
    }

    private static class InsertSMSAsyncTask extends AsyncTask<SMSEntity, Void, Void> {

        private SMSDao scanDao;

        private InsertSMSAsyncTask(SMSDao userDao) {
            this.scanDao = userDao;
        }

        @Override
        protected Void doInBackground(SMSEntity... scanEntities) {
            scanDao.saveSms(scanEntities);
            return null;
        }
    }


    private static class DeleteUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {

        private UserDao scanDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.scanDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... scanEntities) {
            scanDao.deleteUser(scanEntities[0]);
            return null;
        }
    }

    /*private class GetActiveUsers extends AsyncTask<Void, Void, List<UserEntity>> {

        private UserDao scanDao;

        private GetActiveUsers(UserDao userDao) {
            this.scanDao = userDao;
        }

        @Override
        protected List<UserEntity> doInBackground(Void... voids) {
            return scanDao.getActiveUser();
        }

        @Override
        protected void onPostExecute(List<UserEntity> aVoid) {
            super.onPostExecute(aVoid);
            activeUsers = aVoid;
        }
    }

    private static class GetKeyAsyncTask extends AsyncTask<Void, Void, Void> {

        private SMSKEYDao scanDao;
        String key;

        private GetKeyAsyncTask(SMSKEYDao userDao, String key) {
            this.scanDao = userDao;
            this.key = key;
        }

        @Override
        protected Void doInBackground(Void... scanEntities) {
            scanDao.getKey(key);
            return null;
        }
    }*/
}
