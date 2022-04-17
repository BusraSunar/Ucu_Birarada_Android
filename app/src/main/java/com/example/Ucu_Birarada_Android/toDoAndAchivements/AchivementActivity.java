package com.example.Ucu_Birarada_Android.toDoAndAchivements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Ucu_Birarada_Android.Adapters.AchievementAdapter;
import com.example.Ucu_Birarada_Android.Models.AchievementModel;
import com.example.Ucu_Birarada_Android.ProfileActivity;
import com.example.Ucu_Birarada_Android.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AchivementActivity extends AppCompatActivity {

    //OnBackPressed Ekle
    private String token;
    private String tokenType;

    private ArrayList<AchievementModel> achievementModelArrayList;
    private ListView achievementListView;
    private AchievementAdapter achievementAdapter;
    public static Context context;
    private final String URL = "http://10.2.36.80:8080/achievement";
    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achivement);

        context = getApplicationContext();
        this.init();
        this.getAchievement();
        Intent intent = getIntent();
        tokenType = intent.getStringExtra("tokenType");
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(AchivementActivity.this , ProfileActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("tokenType", tokenType);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
        finish();
    }

    private void init()
    {
        Intent intent = getIntent();
        tokenType = intent.getStringExtra("tokenType");
        token = intent.getStringExtra("token");
        achievementModelArrayList = new ArrayList<>(10);
        achievementListView = findViewById(R.id.AchievementsListViewID);
    }


    private void getAchievement()
    {

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
                            JSONArray jsonArray = jsonObject.getJSONArray("modelList");
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject jo = jsonArray.getJSONObject(i);

                                System.out.println("DATA FROM ACH: " + i + " " +jo.getString("id") + " " + jo.getString("email") + " " + jo.getString("achievementType") + " " +
                                        jo.getString("description") + " " + jo.getString("percentage") + " " + jo.getString("goal") + " " + jo.getString("occurred") + " " + jo.getString("completed") + " ");

                                achievementModelArrayList.add( new AchievementModel(jo.getString("id") , jo.getString("email") , jo.getString("achievementType"),jo.getString("description"), jo.getString("percentage") , jo.getString("goal") , jo.getString("occurred"), jo.getString("completed")));

                                achievementAdapter = new AchievementAdapter(context , achievementModelArrayList);
                                achievementListView.setAdapter(achievementAdapter);

                            }


                        } catch (JSONException e) {
                            System.out.println("Json ERR ACH: " + e.getLocalizedMessage());
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





}