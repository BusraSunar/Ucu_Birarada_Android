package com.example.Ucu_Birarada_Android;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.Ucu_Birarada_Android.StaticAnket.first_questionnaire_questions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private String email;
    private String password;
    private EditText email_edit , password_edit;
    private CheckBox rememberMe;
    private ProgressDialog dialog;
    private String token;
    private String tokenType;
    private boolean didUserCompleteForm;
    private boolean flag = false;
    private FirebaseAuth auth;
    private final String URL = "http://10.2.37.71:8080/user/signin";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.checkInternet();
        this.init();

        //Sil beni
    }

    private void init()
    {
        email_edit = findViewById(R.id.LoginEditTextEmailID);
        password_edit = findViewById(R.id.LoginEditTextPasswordID);
        rememberMe = findViewById(R.id.rememberMe);
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("rememberMe", "");


        auth = FirebaseAuth.getInstance();

        if(!flag)
        {
            if (checkbox.equals("true"))
            {
                rememberMe.setChecked(true);
                dialog = new ProgressDialog(LoginActivity.this , R.style.AppCompatAlertDialogStyle);
                dialog.setMessage("Loading...");
                dialog.setCancelable(false);
                dialog.setInverseBackgroundForced(false);
                dialog.show();
                String emailFromPref = preferences.getString("email", "def");
                String passFromPref = preferences.getString("password", "def");
                RequestQueue queue = Volley.newRequestQueue(this);
                auth.signInWithEmailAndPassword(emailFromPref, passFromPref)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseFirestore.getInstance().collection("User").document(auth.getCurrentUser().getEmail())
                                            .update("isOnline", "1").addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused)
                                        {

                                            // Instantiate the RequestQueue.
                                            // Post params to be sent to the server
                                            HashMap<String, String> params = new HashMap<String, String>();
                                            params.put("email", emailFromPref);
                                            params.put("username", emailFromPref);
                                            params.put("password", passFromPref);

                                            JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                                                    new Response.Listener<JSONObject>() {
                                                        @Override
                                                        public void onResponse(JSONObject response) {
                                                            JSONObject jsonObject = null;
                                                            try {
                                                                jsonObject = new JSONObject(String.valueOf(response));
                                                                tokenType = jsonObject.getString("tokenType");
                                                                token = jsonObject.getString("token");
                                                                didUserCompleteForm = Boolean.parseBoolean(jsonObject.getString("formCompleted"));
                                                                if(dialog.isShowing())
                                                                dialog.dismiss();

                                                                if (didUserCompleteForm){
                                                                    Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
                                                                    intent.putExtra("token", token);
                                                                    intent.putExtra("tokenType", tokenType);
                                                                    intent.putExtra("email", emailFromPref);
                                                                    intent.putExtra("password", passFromPref);
                                                                    startActivity(intent);
                                                                    finish();
                                                                }
                                                                else {
                                                                    Intent intent = new Intent(LoginActivity.this , first_questionnaire_questions.class);
                                                                    intent.putExtra("token", token);
                                                                    intent.putExtra("tokenType", tokenType);
                                                                    intent.putExtra("email", emailFromPref);
                                                                    intent.putExtra("password", passFromPref);
                                                                    startActivity(intent);
                                                                    finish();
                                                                }
                                                                //Alttaki yorumlu kod json arrayi okur
                                                                // JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                                // for (int i = 0; i < jsonArray.length(); i++) {
                                                                //     JSONObject jo = jsonArray.getJSONObject(i);
                                                                //     System.out.println("Bruh: " + jo.getString("tokenType"));
                                                                // }

                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                                dialog.dismiss();
                                                            }


                                                        }
                                                    }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    VolleyLog.e("Error: ", error.getMessage());
                                                    dialog.dismiss();
                                                }
                                            }){

                                                //Headera g??nder
                                                @Override
                                                public Map<String, String> getHeaders() throws AuthFailureError {
                                                    HashMap<String, String> headers = new HashMap<String, String>();
                                                    //headers.put("Content-Type", "application/json");
                                                    headers.put("winx", "mokoko");
                                                    return headers;
                                                }
                                            };

                                            // add the request object to the queue to be executed
                                            req.setRetryPolicy(new DefaultRetryPolicy(
                                                    0,
                                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                            queue.add(req);
                                            if(dialog.isShowing())
                                            {
                                                dialog.dismiss();
                                            }

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {


                                        }
                                    });
                                } else {
                                    if(dialog.isShowing())
                                    {
                                        dialog.dismiss();
                                    }
                                }
                            }
                        });




            }else
            {
                rememberMe.setChecked(false);
            }
        }


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void checkInternet()
    {
        if (!isNetworkConnected())
        {
            androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(LoginActivity.this).create();
            alertDialog.setTitle("Ba??lant?? Problemi");
            //alertDialog.setIcon(getResources().getDrawable(R.drawable.nonnet));
            alertDialog.setMessage("Cihaz??n??z internete ba??l?? de??il.");
            alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "Tamam",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            System.exit(0);
                        }
                    });
            alertDialog.show();
        }
    }

    private boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static boolean isValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void loginBtnAction(View view)
    {
        dialog = new ProgressDialog(LoginActivity.this , R.style.AppCompatAlertDialogStyle);dialog = new ProgressDialog(LoginActivity.this , R.style.AppCompatAlertDialogStyle);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        // db gelince burasi degiscek // S??L BEN??
        flag = true;
        RequestQueue queue = Volley.newRequestQueue(this);

        email = email_edit.getText().toString();
        password = password_edit.getText().toString();

        if (email.length()==0){
            email_edit.setError("Enter an email");
        }
         if (!isValid(email)){
            email_edit.setError("Enter a valid email");
        }
         if (password.length()<6){
            password_edit.setError("Enter a valid password");
        }




        if(rememberMe.isChecked())
        {
            SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("rememberMe","true");
            editor.putString("email", email_edit.getText().toString());
            editor.putString("password", password_edit.getText().toString());
            editor.apply();
        }
        else
        {
            SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("rememberMe","false");
            editor.putString("email",  "");
            editor.putString("password","");
            editor.apply();
        }



        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("username", email);
        params.put("password", password);

        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Response177: " + response.toString());
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(String.valueOf(response));
                            tokenType = jsonObject.getString("tokenType");
                            token = jsonObject.getString("token");
                            didUserCompleteForm = Boolean.parseBoolean(jsonObject.getString("formCompleted"));

                            if(dialog.isShowing())
                                dialog.dismiss();

                            if (didUserCompleteForm){
                                Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
                                intent.putExtra("token", token);
                                intent.putExtra("tokenType", tokenType);
                                intent.putExtra("email", email);
                                intent.putExtra("password", password);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(LoginActivity.this , first_questionnaire_questions.class);
                                intent.putExtra("token", token);
                                intent.putExtra("tokenType", tokenType);
                                intent.putExtra("email", email);
                                intent.putExtra("password", password);
                                startActivity(intent);
                                finish();
                            }


                            //Alttaki yorumlu kod json arrayi okur
                            // JSONArray jsonArray = jsonObject.getJSONArray("data");
                            // for (int i = 0; i < jsonArray.length(); i++) {
                            //     JSONObject jo = jsonArray.getJSONObject(i);
                            //     System.out.println("Bruh: " + jo.getString("tokenType"));
                            // }
                            // JSONArray jsonArray = jsonObject.getJSONArray("data");
                            // for (int i = 0; i < jsonArray.length(); i++) {
                            //     JSONObject jo = jsonArray.getJSONObject(i);
                            //     System.out.println("Bruh: " + jo.getString("tokenType"));
                            // }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialog.dismiss();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(LoginActivity.this ,"Email veya ??ifre hatal??." , Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        }){

            //Headera g??nder

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("winx", "mokoko");
                return headers;
            }
        };

        // add the request object to the queue to be executed
        req.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(req);



    }

    public void signInBtnAction(View view)
    {
        Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
        startActivity(intent);
        finish();

    }

    public void goToForgotPassword(View view)
    {
        Intent intent = new Intent(LoginActivity.this , ForgotPasswordActivity.class);
        startActivity(intent);
    }

}
