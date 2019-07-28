package com.horizonlabs.smsreader.data.local.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class SMSKeyWord {
    @PrimaryKey(autoGenerate = true)
    int id;
    String keyWord;

    public SMSKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Ignore
    public static SMSKeyWord[] populateData() {
        return new SMSKeyWord[]{
                new SMSKeyWord("+919372885773"),
                new SMSKeyWord("+918093793198")
        };
    }
}
