package com.example.Ucu_Birarada_Android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;


public class ForestActivity extends Activity {

    private Uri fileUri;
    private VrVideoView.Options videoOptions = new VrVideoView.Options();
    protected VrVideoView videoWidgetView;
    ImageButton imageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest);

        //statusText = (TextView) findViewById(R.id.status_text);
        imageButton = (ImageButton) findViewById(R.id.backToMeditationImage);

        videoWidgetView = (VrVideoView) findViewById(R.id.video_view);
        videoWidgetView.setEventListener(new VrVideoEventListener(){
            @Override
            public void onCompletion() {
                VrVideoView.Options options = new VrVideoView.Options();
                options.inputType = VrVideoView.Options.TYPE_MONO;
                try {
                    videoWidgetView.loadVideoFromAsset("forest.mp4", options);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        handleIntent(getIntent());
    }

    /**
     * Called when the Activity is already running and it's given a new intent.
     */
    @Override
    protected void onNewIntent(Intent intent) {

        setIntent(intent);
        handleIntent(intent);
    }


    public void initVRVideo(){

        try {

            VrVideoView.Options options = new VrVideoView.Options();
            options.inputType = VrVideoView.Options.TYPE_MONO;
            videoWidgetView.loadVideoFromAsset("forest.mp4", options);

        } catch (IOException e) {

            videoWidgetView.post(new Runnable() {
                @Override
                public void run() {
                    Toast
                            .makeText(ForestActivity.this, "Error opening file. ", Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
    }

    /**
     * Load custom videos based on the Intent or load the default video. See the Javadoc for this
     * class for information on generating a custom intent via adb.
     */
    private void handleIntent(Intent intent) {
        // Determine if the Intent contains a file to load.
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            //Log.i(TAG, "ACTION_VIEW Intent received");

            fileUri = intent.getData();
            if (fileUri == null) {
                //Log.w(TAG, "No data uri specified. Use \"-d /path/filename\".");
            } else {
                //Log.i(TAG, "Using file " + fileUri.toString());
            }

            videoOptions.inputFormat = intent.getIntExtra("inputFormat", VrVideoView.Options.FORMAT_DEFAULT);
            videoOptions.inputType = intent.getIntExtra("inputType", VrVideoView.Options.TYPE_MONO);
        } else {
            //Log.i(TAG, "Intent is not ACTION_VIEW. Using the default video.");
            fileUri = null;
        }
        initVRVideo();
    }

    @Override
    protected void onDestroy() {
        // Destroy the widget and free memory.
        videoWidgetView.shutdown();
        super.onDestroy();
    }


    public void goBackToMeditation(View view) {
        onPause();
        onDestroy();
        Intent intent = new Intent(ForestActivity.this, denemeActivity.class);
        startActivity(intent);
        finish();
    }
}