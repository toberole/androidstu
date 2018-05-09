package com.world.helllo.activity;

import android.annotation.SuppressLint;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private android.widget.TextView tvtest;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvtest = (TextView) findViewById(R.id.tv_test);

        Log.i("AAAA", "getFilesDir(): " + getFilesDir().toString());
        Log.i("AAAA", "getCacheDir(): " + getCacheDir().toString());
        Log.i("AAAA", "getExternalCacheDir(): " + getExternalCacheDir().toString());
        Log.i("AAAA", "getExternalFilesDir(): " + getExternalFilesDir(android.os.Environment.DIRECTORY_MUSIC).toString());
        Log.i("AAAA", "getDatabasePath(): " + getDatabasePath(android.os.Environment.DIRECTORY_MUSIC).toString());
        Log.i("AAAA", "getExternalFilesDir(): " + getExternalFilesDir(android.os.Environment.DIRECTORY_MUSIC).toString());

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone rt = RingtoneManager.getRingtone(getApplicationContext(), uri);
        rt.play();


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                doto(msg.obj.toString());
            }
        };

//        for (int i = 0; i < 20; i++) {
//            Message msg = Message.obtain();
//            msg.obj = i;
//            handler.sendMessage(msg);
//        }

    }

    private void doto(String msg) {
        Log.i("AAAA", "doto thread name " + Thread.currentThread().getName() + " msg: " + msg);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
