package com.example.workmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public class SecondActivity extends AppCompatActivity {
    TextView tvStatus;
    Button btnStop;
    Button btnPlay;
    Button btnStopPlay;
    OneTimeWorkRequestSingltone oneTimeWorkRequestSingltone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        tvStatus = findViewById(R.id.idTVStatus);
        btnStop = findViewById(R.id.btnStop);
        btnPlay = findViewById(R.id.btnPlay);
        btnStopPlay = findViewById(R.id.btnStopPlay);
        oneTimeWorkRequestSingltone = OneTimeWorkRequestSingltone.getInstance(getApplicationContext());
        WorkManager mWorkManager =oneTimeWorkRequestSingltone.mWorkManager;
        OneTimeWorkRequest mRequest = oneTimeWorkRequestSingltone.mRequest;
        btnStop.setOnClickListener(v -> {
            mWorkManager.getWorkInfoByIdLiveData(mRequest.getId()).observe(this, new Observer<WorkInfo>() {
                @Override
                public void onChanged(@Nullable WorkInfo workInfo) {
                    if (workInfo != null) {
                        WorkInfo.State state = workInfo.getState();
                        tvStatus.append(state.toString() + "\n");
                    }
                }

            });
            oneTimeWorkRequestSingltone.cancelAllWork();
        });

    }
}
