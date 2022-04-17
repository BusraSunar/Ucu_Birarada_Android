package com.example.Ucu_Birarada_Android.MeditationActivities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.Ucu_Birarada_Android.R;
import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

public class SnowActivity extends Activity {

    private Uri fileUri;
    private VrVideoView.Options videoOptions = new VrVideoView.Options();
    protected VrVideoView videoWidgetView;
    ImageButton imageButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow);

        //statusText = (TextView) findViewById(R.id.status_text);
        imageButton = (ImageButton) findViewById(R.id.backToMeditationSnow);
        videoWidgetView = (VrVideoView) findViewById(R.id.video_view);
        videoWidgetView.setEventListener(new VrVideoEventListener(){
            @Override
            public void onCompletion() {
                VrVideoView.Options options = new VrVideoView.Options();
                options.inputType = VrVideoView.Options.TYPE_MONO;
                try {
                    videoWidgetView.loadVideoFromAsset("snow2.mp4", options);
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
            videoWidgetView.loadVideoFromAsset("snow2.mp4", options);

        } catch (IOException e) {

            videoWidgetView.post(new Runnable() {
                @Override
                public void run() {
                    Toast
                            .makeText(SnowActivity.this, "Error opening file. ", Toast.LENGTH_LONG)
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
        videoWidgetView.pauseVideo();

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Prevent the view from rendering continuously when in the background.
        videoWidgetView.pauseRendering();
        // If the video is playing when onPause() is called, the default behavior will be to pause
        // the video and keep it paused when onResume() is called.

    }

    public void goBackToMeditation(View view) {
        onPause();
        onDestroy();
        Intent intent = new Intent(SnowActivity.this, MeditationActivity.class);
        startActivity(intent);
        finish();
    }
}