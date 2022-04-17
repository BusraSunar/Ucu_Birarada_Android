package com.example.Ucu_Birarada_Android.SleepActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.Ucu_Birarada_Android.Models.SleepDataModel;
import com.example.Ucu_Birarada_Android.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SleepCounterActivity extends AppCompatActivity {

    private Chronometer counter;
    private SoundMeter soundMeter;
    private Runnable r;
    private final Handler h = new Handler();
    private ArrayList<SleepDataModel> soundData;
    private String token;
    private String tokenType;
    private final String URL = "http://10.2.36.80:8080/sleep";
    private final String URLFORACH = "http://10.2.36.80:8080/sleep";
    private String email;
    private String password;
    private Instant start = Instant.now();
    private long minutes;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_counter);

        soundMeter = new SoundMeter();
        this.init();

    }


    //Life Actions


    //Public Actions



    @Override
    public void onBackPressed()
    {
        counter.stop();
        soundMeter.stop();
        h.removeCallbacks(r);
        postData();

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        minutes = (timeElapsed.toMillis() / 1000) / 60;
        System.out.println("Time taken: "+ minutes +" dk");

        Intent intent = new Intent(SleepCounterActivity.this , SleepActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("tokenType", tokenType);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
        finish();
    }


    //Private Actions
    private void init() {
        Intent intent = getIntent();
        tokenType = intent.getStringExtra("tokenType");
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        soundData = new ArrayList<>();
        r = new Runnable()
        {
            @Override
            public void run()
            {
                //TODO: save sound data
                Double sou = soundMeter.getAmplitude();
                System.out.println("Ses: " + sou);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E MMM d H:m:s O u", Locale.ENGLISH);
                OffsetDateTime odt = OffsetDateTime.parse(new Date().toString(), dtf);
                soundData.add(new SleepDataModel(odt.toString() , sou));
                //System.out.println("DATE: " + new Date());
                h.postDelayed(this, 1000);
            }
        };

        counter = (Chronometer) findViewById(R.id.counter); // initiate a chronometer
        counter.start();
        startVoiceDetection();
    }


    public void stopButtonAction(View view){
        counter.stop();
        soundMeter.stop();
        h.removeCallbacks(r);
        postData();

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        minutes = (timeElapsed.toMillis() / 1000) / 60;
        System.out.println("Time taken: "+ minutes +" dk");

        Intent intent = new Intent(SleepCounterActivity.this , SleepActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("tokenType", tokenType);
        startActivity(intent);
        finish();
    }

    private void postData(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        Gson gson = new Gson();

        String listString = gson.toJson(
                soundData,
                new TypeToken<ArrayList<SleepDataModel>>() {}.getType());

        try {
            JSONArray jsonArray =  new JSONArray(listString);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONArray jsonArr = null;
        try {
            jsonArr = new JSONArray(listString);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println("ADOOOO: " + jsonArr);
        HashMap<String, JSONArray> params = new HashMap<>();
        params.put("model", jsonArr);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(String.valueOf(response));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        }){

            //Headera gönder
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("Authorization", tokenType + " " + token);
                return headers;
            }
        };
        req.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(req);
    }

    private void postAchivementTime(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        HashMap<String, Long> params = new HashMap<>();
        params.put("minutes", minutes);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,URLFORACH, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(String.valueOf(response));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        }){

            //Headera gönder
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("Authorization", tokenType + " " + token);
                return headers;
            }
        };
        req.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(req);
    }

    private void startVoiceDetection() {
        //TODO: Do soundMeter.stop() control
        soundMeter.start();
        h.postDelayed(r , 1000); // 1 second delay (takes millis)
    }




}
