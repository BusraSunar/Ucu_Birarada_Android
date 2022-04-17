package com.example.Ucu_Birarada_Android.SleepActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.Ucu_Birarada_Android.ChatActivities.ChatActivity;
import com.example.Ucu_Birarada_Android.HomeActivity;
import com.example.Ucu_Birarada_Android.MeditationActivities.MeditationActivity;
import com.example.Ucu_Birarada_Android.ProfileActivity;
import com.example.Ucu_Birarada_Android.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SleepActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private SoundMeter soundMeter;
    private BottomNavigationView bottomNavigationView;
    //private LineChart chart;
    private TextView dateView, timeView;

    private String token;
    private String tokenType;
    private String email;
    private String password;

    private final static String URL = "http://10.2.37.108:8080/sleep/mobile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        this.checkInternet();

        this.init();
        //this.initGrap();

        System.out.println("Önemli Token::::" + token + "  " + tokenType);
        this.getData();
    }


    //Life Actions

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Intent intent = null;

            switch (item.getItemId()){
                case R.id.sleep:
                    intent = new Intent(SleepActivity.this , SleepActivity.class);
                    intent.putExtra("token", token);
                    intent.putExtra("tokenType", tokenType);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);

                    startActivity(intent);
                    break;
                case R.id.yoga:
                    intent = new Intent(SleepActivity.this , MeditationActivity.class);
                    intent.putExtra("token", token);
                    intent.putExtra("tokenType", tokenType);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    break;
                case R.id.chat:
                    intent = new Intent(SleepActivity.this , ChatActivity.class);
                    intent.putExtra("token", token);
                    intent.putExtra("tokenType", tokenType);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    break;
                case R.id.profile:
                    intent = new Intent(SleepActivity.this , ProfileActivity.class);
                    intent.putExtra("token", token);
                    intent.putExtra("tokenType", tokenType);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    break;
            }
            //getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            return true;
        }
    };

    //Grafiği başlatır dataları yükler.

    //Public Actions

    //Private Actions
    private void init() {
       // chart = (LineChart) findViewById(R.id.SleepActivityChartID); // Önemsiz dese bile bu activityde viewları cast et.
        bottomNavigationView = findViewById(R.id.bottomNav);// <--  Bu hariç
        bottomNavigationView.setSelectedItemId(R.id.sleep);
        dateView = (TextView) findViewById(R.id.SleepActivityDateTextID);;
        timeView = (TextView) findViewById(R.id.SleepActivityTimeTextID);;
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        Intent intent = getIntent();
        tokenType = intent.getStringExtra("tokenType");
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

    }
/*
    private void initGrap() {

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("PZT", "SAL", "ÇAR", "PER", "CUM", "CMT","PAZ"));
        List<Entry> incomeEntries = getIncomeEntries();
        dataSets = new ArrayList<>();
        LineDataSet set1;

        set1 = new LineDataSet(incomeEntries, "Uyku Kalitesi");
        set1.setColor(ContextCompat.getColor(SleepActivity.this,R.color.green));
        dataSets.add(set1);

        //customization

        chart.setExtraBottomOffset(1);
        chart.fitScreen();
        chart.setScaleEnabled(false);
        chart.setTouchEnabled(false);
        chart.setDragEnabled(false);
        chart.setPinchZoom(false);
        chart.setPinchZoom(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setDrawGridBackground(false);

        //to hide background lines
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);

        //to hide right Y and top X border
        YAxis rightYAxis = chart.getAxisRight();
        rightYAxis.setEnabled(false);


        XAxis xAxis = chart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setGranularity(1f);
        xAxis.setEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(360-45);

        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setDrawFilled(true);
        set1.setFillColor(ContextCompat.getColor(SleepActivity.this,R.color.green));
        set1.setLineWidth(2f);
        set1.setCircleRadius(3f);
        set1.setCircleColor(-7829368);
        set1.setCircleHoleColor(-7829368);
        set1.setDrawValues(false);

        chart.setViewPortOffsets(0f, 0f, 0f, 0f);

        //String setter in x-Axis
        chart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));
        LineData data = new LineData(dataSets);
        chart.setData(data);
        chart.invalidate();
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);

    }

    //Get Data
    private List<Entry> getIncomeEntries() {
        ArrayList<Entry> incomeEntries = new ArrayList<>();

        incomeEntries.add(new Entry(0, 8));
        incomeEntries.add(new Entry(1, 11));
        incomeEntries.add(new Entry(2, 13));
        incomeEntries.add(new Entry(3, 7));
        incomeEntries.add(new Entry(4, 9));
        incomeEntries.add(new Entry(5, 10));
        incomeEntries.add(new Entry(6, 6));
        return incomeEntries.subList(0, 7);
    }

*/
    //DB Actions

    //Button Actions

    public void startSleepButtonAction(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO);
        }
        else
        {
            Intent intent = new Intent(SleepActivity.this , SleepCounterActivity.class);
            intent.putExtra("token", token);
            intent.putExtra("tokenType", tokenType);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            startActivity(intent);
            finish();
        }
    }

    public void sundayButtonAction(View view) {
        System.out.println("1");
    }
    public void mondayButtonAction(View view) {
        System.out.println("2");

    }
    public void tuesdayButtonAction(View view) {
        System.out.println("3");

    }
    public void wednesdayButtonAction(View view) {
        System.out.println("4");

    }
    public void thursdayButtonAction(View view) {
        System.out.println("5");

    }
    public void fridayButtonAction(View view) {
        System.out.println("6");

    }
    public void saturdayButtonAction(View view) {
        System.out.println("7");

    }

    //Check Internet
    @SuppressLint("UseCompatLoadingForDrawables")
    private void checkInternet() {
        if (!isNetworkConnected())
        {
            AlertDialog alertDialog = new AlertDialog.Builder(SleepActivity.this).create();
            alertDialog.setTitle("Bağlantı Problemi");
            alertDialog.setIcon(getResources().getDrawable(R.drawable.nowifi));
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_RECORD_AUDIO: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(SleepActivity.this , SleepCounterActivity.class);
                    intent.putExtra("token", token);
                    intent.putExtra("tokenType", tokenType);
                    startActivity(intent);
                    finish();
                } else {
                    //TODO: Do something on voice record permission rejection
                }
                return;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SleepActivity.this , HomeActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("tokenType", tokenType);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
        finish();
    }


    //Devam edilecek
    private void getData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<>();
        params.put("none", "none");

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(String.valueOf(response));
                            JSONArray jsonArray = jsonObject.getJSONArray("list");
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject jo = jsonArray.getJSONObject(i);

                                System.out.println("SLEEP GET: " + jo.toString());

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                System.out.println("ERRRRROR: " + error.getLocalizedMessage());
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

        queue.add(req);
    }

}