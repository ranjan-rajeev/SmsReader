package com.horizonlabs.smsreader.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.horizonlabs.smsreader.data.SMSRepository;
import com.horizonlabs.smsreader.data.local.model.SMSKeyWord;
import com.horizonlabs.smsreader.data.local.model.UserEntity;

import java.util.List;

public class KeywordViewModel extends AndroidViewModel {

    private SMSRepository userRepository;

    private LiveData<List<SMSKeyWord>> allUser;

    public KeywordViewModel(@NonNull Application context) {
        super(context);
        userRepository = new SMSRepository(context);
        allUser = userRepository.getAllKey();
    }


    public void insertUser(SMSKeyWord... userEntities) {
        userRepository.insertKey(userEntities);
    }

    public void update(UserEntity userEntity) {
        // userRepository.update(userEntity);
    }

    public LiveData<List<SMSKeyWord>> getAllUser() {
        if (allUser == null) return userRepository.getAllKey();
        return allUser;
    }
}
