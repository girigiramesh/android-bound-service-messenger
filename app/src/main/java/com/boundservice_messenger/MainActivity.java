package com.boundservice_messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
//    private Button btn_binding, btn_once, btn_again;
    Messenger messenger = null;
    boolean isBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btn_binding = (Button) findViewById(R.id.btn_binding);
//        btn_once = (Button) findViewById(R.id.btn_once);
//        btn_again = (Button) findViewById(R.id.btn_again);

    }

    public void bindService(View view) {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void sayHello(View view) {
        if (isBind) {
            String button_text;
            button_text = (String) ((Button) view).getText();
            if (button_text.equals("Say Hello")) {
                Message message = Message.obtain(null, MyService.JOB_1, 0, 0, 0);
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else if (button_text.equals("Say Hello Again")) {
                Message message = Message.obtain(null, MyService.JOB_2, 0, 0, 0);
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        } else {
            Toast.makeText(getApplicationContext(), "Bind Service First..!", Toast.LENGTH_LONG).show();
        }

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            messenger = new Messenger(iBinder);
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            messenger = null;
            isBind = false;
        }
    };

    @Override
    protected void onStop() {
        unbindService(serviceConnection);
        isBind = false;
        messenger = null;
        super.onStop();
    }
}
