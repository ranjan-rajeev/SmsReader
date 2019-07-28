package com.horizonlabs.smsreader.data.local.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class SMSEntity {

    @PrimaryKey(autoGenerate = true)
    int id;
    String smsFrom;
    String smsBody;
    long smsTimeStamp;

    public int getId() {
        return id;
    }

    public SMSEntity(String smsFrom, String smsBody, long smsTimeStamp) {
        this.smsFrom = smsFrom;
        this.smsBody = smsBody;
        this.smsTimeStamp = smsTimeStamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSmsFrom() {
        return smsFrom;
    }

    public void setSmsFrom(String smsFrom) {
        this.smsFrom = smsFrom;
    }

    public String getSmsBody() {
        return smsBody;
    }

    public void setSmsBody(String smsBody) {
        this.smsBody = smsBody;
    }

    public long getSmsTimeStamp() {
        return smsTimeStamp;
    }

    public void setSmsTimeStamp(long smsTimeStamp) {
        this.smsTimeStamp = smsTimeStamp;
    }

    public static SMSEntity[] populateData() {
        return new SMSEntity[]{
                new SMSEntity("91732885773", "Welcome", Calendar.getInstance().getTimeInMillis()),
                new SMSEntity("91732885773", "Welcome", Calendar.getInstance().getTimeInMillis()),
                new SMSEntity("91732885773", "Welcome", Calendar.getInstance().getTimeInMillis())
        };
    }
}
