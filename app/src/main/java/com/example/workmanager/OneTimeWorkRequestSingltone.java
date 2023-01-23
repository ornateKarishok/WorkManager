package com.example.workmanager;

import android.content.Context;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class OneTimeWorkRequestSingltone {
    private static OneTimeWorkRequestSingltone single_instance = null;
    public WorkManager mWorkManager;

    public OneTimeWorkRequest mRequest;

    private OneTimeWorkRequestSingltone(Context context) {
        mWorkManager = WorkManager.getInstance(context);
        mRequest = new OneTimeWorkRequest.Builder(UploadWorker.class).addTag("mytag").build();
    }

    public static OneTimeWorkRequestSingltone getInstance(Context context) {
        if (single_instance == null) {
            single_instance = new OneTimeWorkRequestSingltone(context);
        }

        return single_instance;
    }
    public void cancelAllWork() {
        mWorkManager.cancelAllWork();
    }
}
