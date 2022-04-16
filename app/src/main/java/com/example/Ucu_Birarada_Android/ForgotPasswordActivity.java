package com.example.Ucu_Birarada_Android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText forgotEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgotEmail = (EditText) findViewById(R.id.forgotPassEmailEditText);
    }

    public void sendEmailAddress(View view) {
        String str_email = forgotEmail.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);

        final String URL = "http://10.2.36.78:8080/user/forgotPass?email=" + str_email;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", str_email);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("FORGOTPASSWORD 60  " + response);
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
                forgotEmail.setError("Hatali mail adresi");
                VolleyLog.e("Error: ", error.getMessage());
            }
        }){

        };
        queue.add(req);

    }
}