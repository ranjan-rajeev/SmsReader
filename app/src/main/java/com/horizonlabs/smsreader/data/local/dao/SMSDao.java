package com.horizonlabs.smsreader.data.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.horizonlabs.smsreader.data.local.model.SMSEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan -  ABPB on 27-07-2019.
 */
@Dao
public interface SMSDao {
    @Query("SELECT * FROM SMSEntity")
    LiveData<List<SMSEntity>> getAllSMS();

    @Query("SELECT * FROM SMSEntity where  id = :itemId")
    LiveData<SMSEntity> getSMsFromID(int itemId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveSms(SMSEntity... smsEntities);

    @Update
    void updateSMS(SMSEntity smsEntity);
}
