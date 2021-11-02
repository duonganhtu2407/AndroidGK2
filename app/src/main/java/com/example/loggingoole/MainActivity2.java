package com.example.loggingoole;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity2 extends AppCompatActivity  {

    private  ServerMyClass myService;
    private  boolean isBound = false;
    private ServiceConnection connection;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final  Button btnOn = (Button) findViewById(R.id.btOn);
        final Button btnOff = (Button) findViewById(R.id.btOff);

        connection = new ServiceConnection() {


            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                ServerMyClass.MyBinder binder = (ServerMyClass.MyBinder) service;
                myService = binder.getService();
                isBound = true;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBound = false;
            }
        };
        final Intent intent = new Intent(MainActivity2.this, ServerMyClass.class);
        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBound){
                    unbindService(connection);
                    isBound = false;
                }
            }
        });
//        btnFast.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isBound){
//                    myService.fastForward();
//                }else {
//                    Toast.makeText(MainActivity2.this,"Service chua hoat dong", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
        findViewById(R.id.btStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBound){
                    myService.fastStart();
                }else{
                    Toast.makeText(MainActivity2.this,"Service chua hoat dong", Toast.LENGTH_SHORT).show();
                }
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        findViewById(R.id.button_sign_out).setOnClickListener(v -> signOut());
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       logout();
                    }


                });
    }

    private void logout() {
        startActivity(new Intent(this,MainActivity.class));
    }




}