package com.example.workmanager;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class AudioRecorderUtil {
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private static String mFilePath = null;
    private static AudioRecorderUtil audioRecorderUtil = null;
    private boolean isRecording = false;

    public static AudioRecorderUtil getInstance() {
        if (audioRecorderUtil == null) {
            audioRecorderUtil = new AudioRecorderUtil();
        }

        return audioRecorderUtil;
    }

    AudioRecorderUtil() {
        mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String FILE_NAME = "/AudioRecording.OGG";
        mFilePath += FILE_NAME;

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        new File(mFilePath);
        mRecorder.setOutputFile(mFilePath);
    }


    public void startRecording() {
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder.start();
        isRecording = true;
    }

    public void playAudio() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFilePath);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e("TAG", "prepare() failed");
        }
    }

    public void pauseRecording() {
        mRecorder.stop();
        isRecording = false;
        mRecorder.release();
        mRecorder = null;
    }

    public void pausePlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    public boolean isRecording() {
        return isRecording;
    }
}
