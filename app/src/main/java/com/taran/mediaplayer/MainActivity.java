package com.taran.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarVolume , seekBarMusic;
    private Button btnChangeMusic, btnChangeVideo;
    private TextView txtNamePlay;
    private ImageButton btnPlay, btnPause, btnRepeatAll, btnResetMusic;
    private MediaPlayer musicPlayer;
    private VideoView videoView;
    private MediaController mediaController;
    private AudioManager audioManager;
    boolean music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarMusic = findViewById(R.id.seekBarMusic);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        btnChangeMusic = findViewById(R.id.btnChangeMusic);
        btnChangeVideo = findViewById(R.id.btnChangeVideo);
        txtNamePlay = findViewById(R.id.txtNamePlay);
        btnPlay = findViewById(R.id.btnPlay);
        btnRepeatAll = findViewById(R.id.btnRepeatAll);
        btnResetMusic = findViewById(R.id.btnResetMusic);
        btnPause = findViewById(R.id.btnPause);
        videoView = findViewById(R.id.videoView);
        music = true;

        musicPlayer = MediaPlayer.create(this, R.raw.asheghanenist);

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videoplayer);
        videoView.setVideoURI(videoUri);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maximumVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBarVolume.setMax(maximumVolume);
        seekBarVolume.setProgress(currentVolume);

        //seekBarVolume
        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser){
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC , progress ,0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        // TODO : set controller music
//        //seekBar Control music
//        seekBarMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

        // TODO : controller
      //  mediaController = new MediaController(MainActivity.this);

        //change page 1
        btnChangeMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.setVisibility(View.VISIBLE);
                btnChangeMusic.setVisibility(View.INVISIBLE);
                btnChangeVideo.setVisibility(View.VISIBLE);
                seekBarMusic.setVisibility(View.INVISIBLE);
                musicPlayer.stop();
                txtNamePlay.setText("video player");
                btnPause.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
                music = false;


            }
        });



        //change page 2
        btnChangeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.setVisibility(View.INVISIBLE);
                btnChangeVideo.setVisibility(View.INVISIBLE);
                btnChangeMusic.setVisibility(View.VISIBLE);
                seekBarMusic.setVisibility(View.VISIBLE);
                txtNamePlay.setText("music player");
                videoView.pause();
                btnPause.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
                music = true;
            }
        });


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (music == true) {
                    musicPlayer.start();
                    Toast.makeText(MainActivity.this , "by" , Toast.LENGTH_SHORT).show();


                } else if (music == false) {
                    videoView.start();
                    Toast.makeText(MainActivity.this , "hi" , Toast.LENGTH_SHORT).show();
                }

                btnPause.setVisibility(View.VISIBLE);
                btnPlay.setVisibility(View.INVISIBLE);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (music == true) {
                    musicPlayer.pause();
                }else if (music == false){
                    videoView.pause();
                }
                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.INVISIBLE);
            }
        });


        btnResetMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                musicPlayer.stop();
                try {
                    musicPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.INVISIBLE);

            }
        });

        // TODO : set later repeat
//        btnRepeatAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }


}