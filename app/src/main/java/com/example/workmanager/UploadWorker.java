package com.example.workmanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class UploadWorker extends Worker {
    private static final String WORK_RESULT = "work_result";

    public UploadWorker(@NonNull Context context,
                        @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data taskData = getInputData();
        AudioRecorderUtil audioRecorderUtil = new AudioRecorderUtil();
        audioRecorderUtil.startRecording();
       // String taskDataString = taskData.getString(MainActivity.MESSAGE_STATUS);
      //  showNotification("WorkManager", taskDataString != null ? taskDataString : "Message has been Sent");
        // outputData = new Data.Builder().putString(WORK_RESULT, "Jobs Finished").build();
        if(isStopped()) {
            return Result.success();
        }
        return null;
    }
//    private void showNotification(String task, String desc) {
//        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//        String channelId = "task_channel";
//        String channelName = "task_name";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new
//                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
//            manager.createNotificationChannel(channel);
//        }
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
//                .setContentTitle(task)
//                .setContentText(desc)
//                .setSmallIcon(R.mipmap.ic_launcher);
//        manager.notify(1, builder.build());
//    }


    @Override
    public void onStopped() {
        AudioRecorderUtil audioRecorderUtil = new AudioRecorderUtil();
        audioRecorderUtil.pauseRecording();
        Log.e("WORKMANAGERTAG", "onStop");
        System.out.println("work Manager Stopped");
        super.onStopped();
    }

}