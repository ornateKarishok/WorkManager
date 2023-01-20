package com.example.workmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private WorkRequest uploadWorkRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class)
                .build();

//        uploadWorkRequest = new PeriodicWorkRequest.Builder(UploadWorker.class,
//                15*60*1000, //15 mins is minimum
//                TimeUnit.MILLISECONDS)
//                .build();
    }

    public void buttonWorkManager(View view){
        WorkManager
                .getInstance(this)
                .enqueue(uploadWorkRequest);

        textView.setText("Work Manager executed!");
    }
}