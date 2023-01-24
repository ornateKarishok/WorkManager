package com.example.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class VoiceRecorderWorker extends Worker {
    private static final String WORK_MANAGER_TAG = "WORK_MANAGER_TAG";

    public VoiceRecorderWorker(@NonNull Context context,
                               @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        AudioRecorderUtil audioRecorderUtil = AudioRecorderUtil.getInstance();
        audioRecorderUtil.startRecording();
        do {
            Log.e(WORK_MANAGER_TAG, "Recording....");
        } while (audioRecorderUtil.isRecording());
        if (isStopped()) {
            return Result.retry();
        } else {
            return Result.success();
        }
    }


    @Override
    public void onStopped() {
        Log.e(WORK_MANAGER_TAG, "onStop");
        super.onStopped();
    }

}