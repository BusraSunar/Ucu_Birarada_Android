package com.example.Ucu_Birarada_Android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class denemeActivity extends AppCompatActivity {

    Button forest;
    Button snow;
    Button beach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme);
        forest = (Button) findViewById(R.id.forestButton);
        snow = (Button) findViewById(R.id.snowButton);
        beach = (Button) findViewById(R.id.BeachButton);
    }

    public void goToForestScene(View view) {

        Intent intent = new Intent(denemeActivity.this, ForestActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToBeachScene(View view) {
        Intent intent = new Intent(denemeActivity.this, BeachActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToSnowScene(View view) {
        Intent intent = new Intent(denemeActivity.this, SnowActivity.class);
        startActivity(intent);
        finish();
    }
}