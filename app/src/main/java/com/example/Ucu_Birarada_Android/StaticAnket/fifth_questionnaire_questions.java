package com.example.Ucu_Birarada_Android.StaticAnket;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.Ucu_Birarada_Android.HomeActivity;
import com.example.Ucu_Birarada_Android.MeditationActivities.MeditationActivity;
import com.example.Ucu_Birarada_Android.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class fifth_questionnaire_questions extends AppCompatActivity {

    private ArrayList<HashMap<String,String>> answerForm;
    private ArrayList<RadioGroup> radioGroups;
    private ArrayList<RelativeLayout> containers;

    private String token;
    private String tokenType;
    private String email;
    private String password;
    private final String URL = "http://10.2.37.71:8080/question/submitAnswers";
    public static Context context;
    private int answered;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        answered = 0;
        super.onCreate(savedInstanceState);
        Bundle extra = getIntent().getBundleExtra("answers");
        answerForm = (ArrayList<HashMap<String,String>>) extra.getSerializable("object");
        setContentView(R.layout.activity_fifth_questionnaire_questions);
        this.checkInternet();
        this.init();
        context = getApplicationContext();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null)
        {
            tokenType = intent.getStringExtra("tokenType");
            token = intent.getStringExtra("token");
            email = intent.getStringExtra("email");
            password = intent.getStringExtra("password");
        }


        //Buradan -- yaz
        System.out.println("ANKET 5: "+ email + " " + password);
    }



    //private metotlar
    private void init()
    {

        HashMap<String,String> answer = new HashMap<>();
        answer.put("questionBody","I don't mind being the center of attention.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I feel others' emotions.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I follow a schedule.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I get irritated easily.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I spend time reflecting on things.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I am quiet around strangers.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I make people feel at ease.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I am exacting in my work.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I often feel blue.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I am full of ideas.");
        answer.put("answer","");
        answerForm.add(answer);

        containers = new ArrayList<>();
        radioGroups = new ArrayList<RadioGroup>(25);
        radioGroups.add(findViewById(R.id.FifthQuestionnaireFirstItemRadioGroupID));

        radioGroups.get(0).setVisibility(View.VISIBLE);
        radioGroups.get(0).setOnCheckedChangeListener(new FadeOnCheckedListener());

        RelativeLayout iterate = findViewById(R.id.FifthQuestionnaireFirstContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FifthQuestionnaireSecondItemRadioGroupID));
        radioGroups.get(1).setVisibility(View.GONE);
        radioGroups.get(1).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FifthQuestionnaireSecondContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FifthQuestionnaireThirdItemRadioGroupID));
        radioGroups.get(2).setVisibility(View.GONE);
        radioGroups.get(2).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FifthQuestionnaireThirdContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FifthQuestionnaireFourthItemRadioGroupID));
        radioGroups.get(3).setVisibility(View.GONE);
        radioGroups.get(3).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FifthQuestionnaireFourthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FifthQuestionnaireFifthItemRadioGroupID));
        radioGroups.get(4).setVisibility(View.GONE);
        radioGroups.get(4).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FifthQuestionnaireFifthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FifthQuestionnaireSixthItemRadioGroupID));
        radioGroups.get(5).setVisibility(View.GONE);
        radioGroups.get(5).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FifthQuestionnaireSixthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FifthQuestionnaireSeventhItemRadioGroupID));
        radioGroups.get(6).setVisibility(View.GONE);
        radioGroups.get(6).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FifthQuestionnaireSeventhContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FifthQuestionnaireEighthItemRadioGroupID));
        radioGroups.get(7).setVisibility(View.GONE);
        radioGroups.get(7).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FifthQuestionnaireEighthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FifthQuestionnaireNinethItemRadioGroupID));
        radioGroups.get(8).setVisibility(View.GONE);
        radioGroups.get(8).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FifthQuestionnaireNinethContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FifthQuestionnaireTenthItemRadioGroupID));
        radioGroups.get(9).setVisibility(View.GONE);
        radioGroups.get(9).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FifthQuestionnaireTenthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        Button next = findViewById(R.id.FifthQuestionnaireNextButtonID);
        next.setOnClickListener(new NextPageOnClickListener());

    }
    class NextPageOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (answered < 10 ){
                Toast.makeText(fifth_questionnaire_questions.this,"Please answer all questions before moving to next page.",Toast.LENGTH_SHORT).show();
            }
            else {
                RequestQueue queue = Volley.newRequestQueue(context);
                // Post params to be sent to the server
                HashMap<String, ArrayList<HashMap<String,String>>> params = new HashMap<>();
                params.put("payload", answerForm);

                JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("Response158: " + response.toString());
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(String.valueOf(response));




                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
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

                // add the request object to the queue to be executed
                req.setRetryPolicy(new DefaultRetryPolicy(
                        0,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(req);

                Intent intent = new Intent(fifth_questionnaire_questions.this, HomeActivity.class);            Bundle extra = new Bundle();
                extra.putSerializable("object",answerForm);
                intent.putExtra("token", token);
                intent.putExtra("tokenType", tokenType);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);


            }

        }
    }
    class FadeOnCheckedListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int currentLocation = 0;
            radioGroup.setVisibility(View.GONE);
            for (int y = 0; y < radioGroups.size(); y++){
                currentLocation = y + 40;
                if (radioGroups.get(y).equals(radioGroup)){
                    if (answerForm.get(currentLocation).get("answer").equals("")){
                        answered++;
                    }
                    switch (i%5){
                        case 0:
                            answerForm.get(currentLocation).put("answer","Completely Agree");
                            break;
                        case 1:
                            answerForm.get(currentLocation).put("answer","Agree");
                            break;
                        case 2:
                            answerForm.get(currentLocation).put("answer","Nor Agree Nor Disagree");
                            break;
                        case 3:
                            answerForm.get(currentLocation).put("answer","Disagree");
                            break;
                        case 4:
                            answerForm.get(currentLocation).put("answer","Completely Disagree");
                            break;
                    }
                    if (y != radioGroups.size() -1){
                        radioGroups.get(y+1).setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    class AppearOnClickListener implements RelativeLayout.OnClickListener{

        @Override
        public void onClick(View view) {
            RelativeLayout layout = (RelativeLayout) view;
            for (RadioGroup fade : radioGroups){
                fade.setVisibility(View.GONE);
            }
            RadioGroup toAppear = (RadioGroup) layout.getChildAt(1);
            toAppear.setVisibility(View.VISIBLE);

        }
    }


    //database metotları






    //misc


    @SuppressLint("UseCompatLoadingForDrawables")
    private void checkInternet()
    {
        if (!isNetworkConnected())
        {
            androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(fifth_questionnaire_questions.this).create();
            alertDialog.setTitle("Bağlantı Problemi");
            //alertDialog.setIcon(getResources().getDrawable(R.drawable.nonnet));
            alertDialog.setMessage("Cihazınız internete bağlı değil.");
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









}




