package com.horizonlabs.smsreader.data.local.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {

    public UserEntity(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @PrimaryKey(autoGenerate = true)
    int id;

    String mobileNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Ignore
    public static UserEntity[] populateData() {
        return new UserEntity[]{
                new UserEntity("9372885773")
        };
    }

}
