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
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

public class SleepActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private BottomNavigationView bottomNavigationView;
    private TextView dateView, timeView;
    private TextView slept, wokeUp, sleepQuality, totalSleep, bestSleepAt, worstSleepAt;
    private String sleptData, wokeUpData, bestSleepAtData, worstSleepAtData;
    private List <Date> sleepTimeList;
    private List <Double> sleepQualityList;
    private String sleepQualityData, totalSleepHoursData, timeListStr, qualityListStr, todaysDate;
    private LineChartView lineChart;
    public static Context context;

    private ArrayList<String> qualityList;
    private ArrayList<String> timeList;





    private String token;
    private String tokenType;
    private String email;
    private String password;

    private final static String URL = "http://10.2.36.80:8080/sleep/mobile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        this.checkInternet();
        context = getApplicationContext();

        this.init();

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
        bottomNavigationView = findViewById(R.id.bottomNav);// <--  Bu hariç
        bottomNavigationView.setSelectedItemId(R.id.sleep);
        dateView = (TextView) findViewById(R.id.SleepActivityDateTextID);
        timeView = (TextView) findViewById(R.id.SleepActivityTimeTextID);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        Intent intent = getIntent();
        tokenType = intent.getStringExtra("tokenType");
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        slept = (TextView) findViewById(R.id.slept);
        wokeUp = (TextView) findViewById(R.id.wokeUp);
        sleepQuality = (TextView) findViewById(R.id.sleepQuality);
        totalSleep = (TextView) findViewById(R.id.totalSleep);
        bestSleepAt = (TextView) findViewById(R.id.bestSleepAt);
        worstSleepAt = (TextView) findViewById(R.id.worstSleepAt);


        qualityList = new ArrayList<>();
        timeList = new ArrayList<>();

        drawLineChart();

    }

    private void drawLineChart(){
        /*ChartData.setAxisXBottom(Axis axisX);
        ColumnChartData.setStacked(boolean isStacked);
        Line.setStrokeWidth(int strokeWidthDp);*/

        List<PointValue> values = new ArrayList();


        values.add(new PointValue(1, 3));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(4, 1));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values);

        line.setCubic(true);
        line.setFilled(false);
        line.setHasLabels(false);
        line.setHasLabelsOnlyForSelected(false);
        line.setHasLines(true);
        line.setHasPoints(false);



        line.setColor(Color.GRAY);
        line.setStrokeWidth(1);
        line.setAreaTransparency(4);
        line.setFilled(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);


        AxisValue a1 = new AxisValue(1);
        AxisValue a2 = new AxisValue(2);
        AxisValue a3 = new AxisValue(3);
        AxisValue a4 = new AxisValue(4);
        AxisValue a5 = new AxisValue(5);

        List<AxisValue> xax = new ArrayList<>();
        xax.add(a1);
        xax.add(a2);
        xax.add(a3);
        xax.add(a4);
        xax.add(a5);

        Axis axisX = new Axis().setValues(xax);
        Axis axisY = new Axis().setValues(xax);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        lineChart = findViewById(R.id.lineChart);
        lineChart.setLineChartData(data);
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        lineChart.setZoomEnabled(false);



    }

    //Get Data


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
                                sleptData = jo.getString("sleepStartTime");
                                wokeUpData = jo.getString("sleepEndTime");
                                bestSleepAtData = jo.getString("bestSleepAt");
                                worstSleepAtData = jo.getString("worstSleepAt");
                                sleepQualityData = jo.getString("averageSleepQuality");
                                totalSleepHoursData = jo.getString("totalSleepHours");
                                todaysDate = jo.getString("date");

                                timeListStr = jo.getString("sleepTimeList");
                                qualityListStr = jo.getString("sleepQualityList");

                                JSONArray time = new JSONArray(timeListStr);
                                JSONArray quality = new JSONArray(qualityListStr);

                                for (int j = 0; j < time.length(); j++){
                                    timeList.add(time.getString(j));
                                    System.out.println("Time List -- " + j + "  ---> " + timeList.get(j));
                                }
                                for (int j = 0; j < quality.length(); j++){
                                    qualityList.add(quality.getString(j));
                                    System.out.println("Quality List -- " + j + "  ---> " + qualityList.get(j));
                                }


                                slept.setText(sleptData.substring(11,16).replace(":" , " : "));
                                wokeUp.setText(wokeUpData.substring(11,16).replace(":" , " : "));
                                bestSleepAt.setText(bestSleepAtData.substring(11,16).replace(":" , " : "));
                                worstSleepAt.setText(worstSleepAtData.substring(11,16).replace(":" , " : "));
                                double temp = Double.parseDouble(sleepQualityData);
                                sleepQuality.setText(new DecimalFormat("##.#").format(temp));
                                totalSleep.setText(totalSleepHoursData);

                                System.out.println("SLEEP GET  ------->    " + jo.toString());


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
        req.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(req);
    }

    public void goToHome(View view) {
        Intent intent = new Intent(SleepActivity.this, HomeActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("tokenType", tokenType);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
        finish();
    }

}