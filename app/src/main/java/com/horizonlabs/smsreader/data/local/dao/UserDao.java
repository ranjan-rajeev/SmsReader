package com.horizonlabs.smsreader.data.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.horizonlabs.smsreader.data.local.model.UserEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan -  ABPB on 27-07-2019.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM UserEntity")
    LiveData<List<UserEntity>> getAllUser();

    @Query("SELECT * FROM UserEntity")
    List<UserEntity> getActiveUser();

    @Query("SELECT * FROM UserEntity where  id = :itemId")
    UserEntity getUserFromUserID(int itemId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUser(UserEntity... userEntities);

    @Update
    void updateUser(UserEntity userEntity);

    @Delete
    void deleteUser(UserEntity userEntity);
}
