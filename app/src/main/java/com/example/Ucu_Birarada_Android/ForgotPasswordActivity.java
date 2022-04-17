package com.example.Ucu_Birarada_Android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
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

        final String URL = "http://10.2.36.80:8080/user/forgotPass?email=" + str_email;
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
        req.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(req);

    }
}