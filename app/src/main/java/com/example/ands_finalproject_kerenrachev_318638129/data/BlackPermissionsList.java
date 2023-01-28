package com.example.ands_finalproject_kerenrachev_318638129.data;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ands_finalproject_kerenrachev_318638129.adapters.ApplicationsAdapter;

import java.util.ArrayList;

public class BlackPermissionsList {

    private static BlackPermissionsList me;
    private Context context;

    private String [] blackList = {
            "READ_CALENDAR",
            "WRITE_CALENDAR",
            "CAMERA",
            "READ_CONTACTS",
            "WRITE_CONTACTS",
            "GET_ACCOUNTS",
            "ACCESS_FINE_LOCATION",
            "ACCESS_COARSE_LOCATION",
            "RECORD_AUDIO",
            "READ_PHONE_STATE",
            "READ_PHONE_NUMBERS",
            "CALL_PHONE",
            "ANSWER_PHONE_CALLS",
            "READ_CALL_LOG",
            "WRITE_CALL_LOG",
            "ADD_VOICEMAIL",
            "USE_SIP",
            "PROCESS_OUTGOING_CALLS",
            "BODY_SENSORS",
            "SEND_SMS",
            "RECEIVE_SMS",
            "READ_SMS",
            "RECEIVE_WAP_PUSH",
            "RECEIVE_MMS",
            "READ_EXTERNAL_STORAGE",
            "WRITE_EXTERNAL_STORAGE",
            "ACCESS_MEDIA_LOCATION",
            "ACCEPT_HANDOVER",
            "ACCESS_BACKGROUND_LOCATION",
            "ACTIVITY_RECOGNITION",
    };

    private BlackPermissionsList(Context context) {
        this.context = context;
    }

    public static BlackPermissionsList getMe() {
        return me;
    }

    public static BlackPermissionsList initHelper(Context context) {
        if (me == null) {
            me = new BlackPermissionsList(context);
        }
        return me;
    }

    public String[] getBlackList() {
        return blackList;
    }
}
