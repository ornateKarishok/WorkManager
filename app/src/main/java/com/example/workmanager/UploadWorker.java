package com.example.workmanager;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class UploadWorker extends Worker {
    public UploadWorker(@NonNull Context context,
                        @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        ContextCompat.getMainExecutor(getApplicationContext()).execute(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        "WorkManager Started ...",
                        Toast.LENGTH_SHORT).show();

                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(
                        Context.WIFI_SERVICE);
                if (wifiManager.isWifiEnabled()){
                    Toast.makeText(getApplicationContext(),
                            "Wifi Enabled",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Wifi Disabled",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        return null;
    }
}