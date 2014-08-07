package com.ic.banking.glass.poc4_hiddenservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class HiddenService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new GraphTask(getApplicationContext()).execute("mateo.hermida");
        return START_STICKY;
    }
}
