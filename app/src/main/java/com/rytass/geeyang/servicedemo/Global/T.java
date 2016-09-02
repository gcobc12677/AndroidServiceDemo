
package com.rytass.geeyang.servicedemo.Global;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.rytass.geeyang.servicedemo.Custom.BroadCastData;
import com.rytass.geeyang.servicedemo.Receiver.SDReceiver;

import java.util.ArrayList;
import java.util.Arrays;

public class T {


    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.trim().equals(C.EMPTY) || str.trim().equals(C.NULL));
    }

    public static void sendBroadcast(BroadCastData data) {
        Intent intentsvr = null;
        try {
            intentsvr = new Intent(SDReceiver.NAME_FILTER);
            intentsvr.putExtra(C.HANDLER_PUT_STRING_KEY, data);
            D.c.sendBroadcast(intentsvr);
        } catch (Exception e) {
            L.e(e);
        }
    }

    public static String listToString(ArrayList<String> values, String separate) {
        if (values == null) {
            return "";
        }
        if (T.isNullOrEmpty(separate)) {
            return null;
        }
        String returnValue = "";
        returnValue = TextUtils.join(separate, values.toArray());
        return returnValue;
    }

    public static ArrayList<String> stringToList(String string, String splitSign) {
        if (T.isNullOrEmpty(string)) {
            return null;
        }
        ArrayList<String> returnValue = new ArrayList<String>();
        returnValue = new ArrayList<String>(Arrays.asList(string.split(splitSign)));
        return returnValue;
    }

    public static ConnectivityManager cm = null;
    public static NetworkInfo info = null;

    public final static boolean isNetworkOK(Context context) {
        boolean isNetworkAvalibale = false;
        try {
            if (context.getSystemService(Context.CONNECTIVITY_SERVICE) != null) {
                cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            }
            if (cm != null && ((info = cm.getActiveNetworkInfo()) != null)) {
                isNetworkAvalibale = info.isConnected() && info.isAvailable() && info.getState() == NetworkInfo.State.CONNECTED;
            }

        } catch (Exception e) {
            L.e(e);
        }
        return isNetworkAvalibale;
    }

}

