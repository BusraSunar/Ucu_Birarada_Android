package com.example.Ucu_Birarada_Android;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.Ucu_Birarada_Android.ChatActivities.ChatActivity;
import com.example.Ucu_Birarada_Android.MeditationActivities.MeditationActivity;
import com.example.Ucu_Birarada_Android.SleepActivity.SleepActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class HomeActivity extends AppCompatActivity {

    //private BottomNavigationView bottomNavigationView;
    private CardView sleepCardView;
    private CardView meditationCardView;
    private CardView chatCardView;
    private RelativeLayout cardViewLayout;


    private String token;
    private String tokenType;
    private FirebaseAuth mAuth;
    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.checkInternet();
        sleepCardView = findViewById(R.id.sleepCardView);
        meditationCardView = findViewById(R.id.meditationCardView);
        chatCardView = findViewById(R.id.chatCardView);

        Intent intent = getIntent();
        tokenType = intent.getStringExtra("tokenType");
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        //bottomNavigationView.setVisibility(View.INVISIBLE);

        sleepCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this , SleepActivity.class);
                intent.putExtra("token", token);
                intent.putExtra("tokenType", tokenType);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
                //bottomNavigationView.setVisibility(View.VISIBLE);

            }
        });

        meditationCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this , MeditationActivity.class);
                intent.putExtra("token", token);
                intent.putExtra("tokenType", tokenType);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);

            }
        });

        chatCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this , ChatActivity.class);

                FirebaseFirestore.getInstance().collection("User")
                        .whereEqualTo("isOnline", "1")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int counter = 0;
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        counter++;

                                    }

                                    intent.putExtra("onlineNumber",""+counter);
                                    intent.putExtra("token", token);
                                    intent.putExtra("tokenType", tokenType);
                                    intent.putExtra("email", email);
                                    intent.putExtra("password", password);

                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

            }
        });

        System.out.println("Önemli Token::::" + token + "  " + tokenType);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            ProgressDialog dialog = new ProgressDialog(HomeActivity.this , R.style.AppCompatAlertDialogStyle);
            dialog.setMessage("Yükleniyor");
            dialog.setCancelable(false);
            dialog.setInverseBackgroundForced(false);
            dialog.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseFirestore.getInstance().collection("User").document(mAuth.getCurrentUser().getEmail())
                                        .update("isOnline", "1").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused)
                                    {
                                        if(dialog.isShowing())
                                        {
                                            dialog.dismiss();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        if(dialog.isShowing())
                                        {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(HomeActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                                if(dialog.isShowing())
                                {
                                    dialog.dismiss();
                                }
                            }
                        }
                    });
        } else {
            // User logged in
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void checkInternet()
    {
        if (!isNetworkConnected())
        {
            AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
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

    //buton metotları geçici



}
