package com.world.helllo.activity;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AudioRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnswi;

    private AudioRecord audioRecord;

    private int audioSource = MediaRecorder.AudioSource.MIC;
    private int sampleRateInHz = 16000;

    // AudioFormat.CHANNEL_CONFIGURATION_STEREO 双通道
    private int channelConfig = AudioFormat.CHANNEL_IN_MONO;// 单通道

    // AudioFormat.ENCODING_PCM_8BIT
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;

    /**
     * bufferSizeInBytes 含义因该是bufferSizeInBytes个单位
     * 当用byte buffer去读取数据的时候 最多读取的 bufferSizeInBytes个字节
     * 当用short buffer去读取数据的时候 最多读取的是 bufferSizeInBytes个short
     */
    private int bufferSizeInBytes = 4000;

    private volatile boolean isOpen = false;

    private volatile boolean terminate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        this.btnswi = (Button) findViewById(R.id.btn_swi);

        btnswi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!isOpen) {
            if (null == audioRecord) {
                int sysMinBufferSize = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);

                // 注意bufferSizeInBytes 不能低于系统要求的阈值
                bufferSizeInBytes = bufferSizeInBytes <= sysMinBufferSize ? sysMinBufferSize : bufferSizeInBytes;

                audioRecord = new AudioRecord(audioSource, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes);
                // audioRecord.
                new Thread_(audioRecord).start();
            }
        } else {
            if (null != audioRecord) {
                audioRecord.stop();
                audioRecord.release();
                audioRecord = null;

                terminate = true;
            }
        }

        isOpen = !isOpen;

        btnswi.setText(isOpen ? "停止" : "开始");
    }

    private class Thread_ extends Thread {
        private AudioRecord audioRecord;

        public Thread_(AudioRecord audioRecord) {
            this.audioRecord = audioRecord;
            this.audioRecord.startRecording();
        }

        @Override
        public void run() {
            super.run();

            byte[] buffer = new byte[bufferSizeInBytes];
            short[] shortBuffer = new short[bufferSizeInBytes];
            while (!terminate) {
                long shortlength = audioRecord.read(shortBuffer, 0, buffer.length);
                Log.i("AAAA", "bufferSizeInBytes: " + bufferSizeInBytes + " read shortlength: " + shortlength * 2);

                long bytelength = audioRecord.read(buffer, 0, buffer.length);
                Log.i("AAAA", "bufferSizeInBytes: " + bufferSizeInBytes + " read bytelength: " + bytelength);
            }
        }
    }
}
