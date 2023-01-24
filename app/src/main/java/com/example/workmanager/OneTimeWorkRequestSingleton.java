package com.example.workmanager;

import android.content.Context;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class OneTimeWorkRequestSingleton {
    private static OneTimeWorkRequestSingleton oneTimeWorkRequestSingleton = null;
    private static final String WORK_MANAGER_TAG = "WORK_MANAGER_TAG";
    public WorkManager mWorkManager;

    public OneTimeWorkRequest mRequest;

    private OneTimeWorkRequestSingleton(Context context) {
        mWorkManager = WorkManager.getInstance(context);
        mRequest = new OneTimeWorkRequest.Builder(VoiceRecorderWorker.class).addTag(WORK_MANAGER_TAG).build();
    }

    public static OneTimeWorkRequestSingleton getInstance(Context context) {
        if (oneTimeWorkRequestSingleton == null) {
            oneTimeWorkRequestSingleton = new OneTimeWorkRequestSingleton(context);
        }

        return oneTimeWorkRequestSingleton;
    }

    public void cancelAllWork(Context context) {
        WorkManager.getInstance(context).cancelAllWorkByTag(WORK_MANAGER_TAG);
    }
}
