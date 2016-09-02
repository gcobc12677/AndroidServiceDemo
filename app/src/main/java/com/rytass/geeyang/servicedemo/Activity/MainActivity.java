package com.rytass.geeyang.servicedemo.Activity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.rytass.geeyang.servicedemo.Global.L;
import com.rytass.geeyang.servicedemo.R;
import com.rytass.geeyang.servicedemo.Service.DemoService;

public class MainActivity extends BaseActivity {
    private static ScrollView scrollView = null;
    private static TextView status = null;
    private Button startService = null;
    private Button stopService = null;
    private Button bindService = null;
    private Button unbindService = null;
    private Button checkServiceExist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initial();
        setListeners();
    }

    @Override
    protected void findViews() {
        super.findViews();
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        status = (TextView) findViewById(R.id.status);
        startService = (Button) findViewById(R.id.startService);
        stopService = (Button) findViewById(R.id.stopService);
        bindService = (Button) findViewById(R.id.bindService);
        unbindService = (Button) findViewById(R.id.unbindService);
        checkServiceExist = (Button) findViewById(R.id.checkServiceExist);
    }

    @Override
    protected void initial() {
        super.initial();
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DemoService.class);
                startService(intent);
            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DemoService.class);
                stopService(intent);
            }
        });

        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DemoService.class);
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
                binded = true;
            }
        });

        unbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binded) {
                    unbindService(conn);
                    binded = false;
                }
            }
        });

        checkServiceExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sendMessage("check service exist: " + String.valueOf(isMyServiceRunning(DemoService.class)));
            }
        });
    }

    /* Bind Service */
    private DemoService demoService = null;
    private boolean binded = false;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            L.d();
            MainActivity.sendMessage("activity onServiceConnected");
            demoService = ((DemoService.MyIBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            L.d();
            MainActivity.sendMessage("activity onServiceDisconnected");
            demoService = null;
        }
    };

    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    status.append("\n");
                    status.append((String) msg.obj);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });
                    break;
            }
        }
    };

    public static void sendMessage(String msg) {
        Message message = new Message();
        message.what = 0;
        message.obj = msg;
        MainActivity.handler.sendMessage(message);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
