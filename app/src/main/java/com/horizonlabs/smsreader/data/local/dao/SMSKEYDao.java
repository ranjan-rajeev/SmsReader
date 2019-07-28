package com.horizonlabs.smsreader.data.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.horizonlabs.smsreader.data.local.model.SMSKeyWord;

import java.util.List;

/**
 * Created by Rajeev Ranjan -  ABPB on 27-07-2019.
 */
@Dao
public interface SMSKEYDao {
    @Query("SELECT * FROM SMSKeyWord")
    LiveData<List<SMSKeyWord>> getAllKeys();

    @Query("SELECT * FROM SMSKeyWord where keyWord=:key")
    SMSKeyWord getKey(String key);

    @Query("SELECT * FROM SMSKeyWord where  id = :itemId")
    SMSKeyWord getKeyFromId(int itemId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveKey(SMSKeyWord... smsKeyWords);

    @Update
    void updateKey(SMSKeyWord smsKeyWord);
}
