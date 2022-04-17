package com.example.Ucu_Birarada_Android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    AnimationDrawable logoAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = (ImageView) findViewById(R.id.anim);
        imageView.setBackgroundResource(R.drawable.splash_anim);
        logoAnimation = (AnimationDrawable)imageView.getBackground();
        logoAnimation.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                // Start your app main activity
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                // close this activity
                finish();
            }
        }, 1000);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void checkInternet()
    {
        if (!isNetworkConnected())
        {
            AlertDialog alertDialog = new AlertDialog.Builder(SplashActivity.this).create();
            alertDialog.setTitle("Bağlantı Problemi");
            //alertDialog.setIcon(getResources().getDrawable(R.drawable.nonnet));
            alertDialog.setMessage("Cihazınız internete bağlı değil.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tamam",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            System.exit(0);
                        }
                    });
            alertDialog.show();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }






}