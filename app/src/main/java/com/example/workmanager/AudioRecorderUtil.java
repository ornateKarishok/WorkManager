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
    private static String mFileName = null;


    public void startRecording() {

        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/AudioRecording.3gp";

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        new File(mFileName);
        mRecorder.setOutputFile(mFileName);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder.start();
    }
    public void playAudio() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e("TAG", "prepare() failed");
        }
    }

    public void pauseRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public void pausePlaying() {
        mPlayer.release();
        mPlayer = null;
    }

}
