package com.horizonlabs.smsreader.utility;

public interface Common {
    interface OTPListener {
        void onOTPReceived(String otp);
    }
}