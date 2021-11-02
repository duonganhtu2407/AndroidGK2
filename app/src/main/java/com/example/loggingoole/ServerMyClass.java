package com.example.loggingoole;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServerMyClass extends Service {
    private MyPlayer myPlayer;
    private IBinder binder;

    @Override
    public  void onCreate(){
        super.onCreate();
        Log.d("ServiceDemo","called onCreate(");

        myPlayer = new MyPlayer( this);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("ServiceDemo","da goi toi onBind");
        myPlayer.play();

        return  binder;
    }



    @Override
    public boolean onUnbind(Intent intent){
        Log.d("ServiceDemo","Da goi onBind()");
        myPlayer.stop();
        return super.onUnbind(intent);
    }

    public void fastForward(){
        myPlayer.fastForward(6000);

    }
    public void fastStart(){
        myPlayer.fastStart();
    }
    public class  MyBinder extends Binder {
        public ServerMyClass getService(){
            return ServerMyClass.this;
        }

    }
}
