package com.example.loggingoole;

import android.content.Context;
import android.media.MediaPlayer;

public class MyPlayer {

    private MediaPlayer mediaPlayer;
    public  MyPlayer(Context context){
        mediaPlayer = MediaPlayer.create(context, R.raw.buonvuongmauao_nguyenhung);
        mediaPlayer.setLooping(true);
    }

    public  void fastForward(int pos){
        mediaPlayer.pause();
    }

    public  void fastStart(){
        mediaPlayer.start();
    }
    public  void play(){
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }
    public void stop(){
        if (mediaPlayer != null){
            mediaPlayer.stop();
        }
    }
}
