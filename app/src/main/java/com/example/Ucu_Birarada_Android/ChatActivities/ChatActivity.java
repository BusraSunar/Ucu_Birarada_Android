package com.example.Ucu_Birarada_Android.ChatActivities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.Ucu_Birarada_Android.HomeActivity;
import com.example.Ucu_Birarada_Android.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChatActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private FirebaseAuth auth;
    private DocumentReference documentReference;
    private FirebaseFirestore firebaseFirestore;
    private TextView textID;

    private String token;
    private String tokenType;
    private String email;
    private String password;



    //Bu metot oncreatete setcontentview'ın hemen altda çağrılmalı

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        textID = (TextView)findViewById(R.id.textID);
        Intent intent = getIntent();
        String counter = intent.getStringExtra("onlineNumber");

        tokenType = intent.getStringExtra("tokenType");
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        textID.setText("Online users: " + counter);

        System.out.println("Önemli Token Chat::::" + token + "  " + tokenType);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ChatActivity.this , HomeActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("tokenType", tokenType);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
        finish();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void checkInternet()
    {
        if (!isNetworkConnected())
        {
            AlertDialog alertDialog = new AlertDialog.Builder(ChatActivity.this).create();
            alertDialog.setTitle("Bağlantı Problemi");
            //alertDialog.setIcon(getResources().getDrawable(R.drawable.nonnet));
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
    public void goToChatAction(View view) {
        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userEmail = firebaseUser.getEmail();

        documentReference = firebaseFirestore.collection("User").document(userEmail);
        documentReference.update("chatClick","1").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        Intent intent = new Intent(ChatActivity.this , ChatMatchActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("tokenType", tokenType);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
        finish();
    }

    public void goBackToHome(View view) {
        Intent intent = new Intent(ChatActivity.this , HomeActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("tokenType", tokenType);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
        finish();
    }
}