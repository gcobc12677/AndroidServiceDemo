
package com.rytass.geeyang.servicedemo.Receiver;

import android.content.BroadcastReceiver;

public abstract class SDReceiver extends BroadcastReceiver {

    public final static String MSG_TYPE = "MessageType";
    public final static String NAME_FILTER = (SDReceiver.class.getPackage() + ".sdreceiver");
    public final static int TYPE_GCM_RECEIVE = 0;

}

