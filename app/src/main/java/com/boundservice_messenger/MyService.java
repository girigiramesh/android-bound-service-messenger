package com.boundservice_messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MyService extends Service {
    Messenger messenger = new Messenger(new InComingHandler());
    static final int JOB_1 = 1;
    static final int JOB_2 = 2;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "Service Binding...", Toast.LENGTH_LONG).show();
        return messenger.getBinder();
    }

    class InComingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case JOB_1:
                    Toast.makeText(getApplicationContext(), "Hello from Job 1", Toast.LENGTH_LONG).show();
                    break;
                case JOB_2:
                    Toast.makeText(getApplicationContext(), "Hello from Job 2", Toast.LENGTH_LONG).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
