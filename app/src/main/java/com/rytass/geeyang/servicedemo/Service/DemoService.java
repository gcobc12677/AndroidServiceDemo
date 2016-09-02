package com.rytass.geeyang.servicedemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.rytass.geeyang.servicedemo.Activity.MainActivity;
import com.rytass.geeyang.servicedemo.Global.L;

/**
 * Created by yangjiru on 2016/8/31.
 */
public class DemoService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        L.d();
        MainActivity.sendMessage("service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.d();
        MainActivity.sendMessage("service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.d();
        MainActivity.sendMessage("service onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.d();
        MainActivity.sendMessage("service onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.d();
        MainActivity.sendMessage("service onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        L.d();
        MainActivity.sendMessage("service onTaskRemoved");
    }

    private final IBinder mBinder = new MyIBinder();
    public class MyIBinder extends Binder{
        public DemoService getService() {
            return  DemoService.this;
        }
    }
}
