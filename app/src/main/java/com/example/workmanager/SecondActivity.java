package com.example.workmanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public class SecondActivity extends AppCompatActivity {
    TextView tvStatus;
    Button btnStop;
    Button btnPlay;
    Button btnStopPlay;
    OneTimeWorkRequestSingleton oneTimeWorkRequestSingltone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        tvStatus = findViewById(R.id.idTVStatus);
        btnStop = findViewById(R.id.btnStop);
        btnPlay = findViewById(R.id.btnPlay);
        btnStopPlay = findViewById(R.id.btnStopPlay);
        AudioRecorderUtil audioRecorderUtil = AudioRecorderUtil.getInstance();
        oneTimeWorkRequestSingltone = OneTimeWorkRequestSingleton.getInstance(getApplicationContext());
        WorkManager mWorkManager = oneTimeWorkRequestSingltone.mWorkManager;
        OneTimeWorkRequest mRequest = oneTimeWorkRequestSingltone.mRequest;
        btnStop.setOnClickListener(v -> {
            oneTimeWorkRequestSingltone.cancelAllWork(getApplicationContext());
            audioRecorderUtil.pauseRecording();
            mWorkManager.getWorkInfoByIdLiveData(mRequest.getId()).observe(this, workInfo -> {
                if (workInfo != null) {
                    WorkInfo.State state = workInfo.getState();
                    tvStatus.append(state + "\n");
                }
            });

        });
        btnPlay.setOnClickListener(v -> audioRecorderUtil.playAudio());
        btnStopPlay.setOnClickListener(v -> audioRecorderUtil.pausePlaying());
    }
}
