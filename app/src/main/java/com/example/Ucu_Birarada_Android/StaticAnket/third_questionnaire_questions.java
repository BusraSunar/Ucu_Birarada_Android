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

import com.example.Ucu_Birarada_Android.MeditationActivities.MeditationActivity;
import com.example.Ucu_Birarada_Android.R;
import java.util.ArrayList;
import java.util.HashMap;

public class third_questionnaire_questions extends AppCompatActivity {

    private ArrayList<HashMap<String,String>> answerForm;
    private ArrayList<RadioGroup> radioGroups;
    private ArrayList<RelativeLayout> containers;

    private String token;
    private String tokenType;
    private String email;
    private String password;
    private int answered;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        answered = 0;
        super.onCreate(savedInstanceState);
        Bundle extra = getIntent().getBundleExtra("answers");
        answerForm = (ArrayList<HashMap<String, String>>) extra.getSerializable("object");
        setContentView(R.layout.activity_third_questionnaire_questions);
        this.checkInternet();
        this.init();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null)
        {
            tokenType = intent.getStringExtra("tokenType");
            token = intent.getStringExtra("token");
            email = intent.getStringExtra("email");
            password = intent.getStringExtra("password");
        }

        System.out.println("ANKET 3: "+ email + " " + password);

        //Buradan -- yaz
    }


    //Button metotları




    //private metotlar
    private void init()
    {
        HashMap<String,String> answer = new HashMap<>();
        answer.put("questionBody","I start conversations.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I am not interested in other people's problems.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I get chores done right away.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I am easily disturbed.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I have excellent ideas.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I have little to say.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I have a soft heart.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I often forget to put things back in their proper place.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I get upset easily.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I do not have a good imagination.");
        answer.put("answer","");
        answerForm.add(answer);


        containers = new ArrayList<>();
        radioGroups = new ArrayList<RadioGroup>(25);
        radioGroups.add(findViewById(R.id.ThirdQuestionnaireFirstItemRadioGroupID));

        radioGroups.get(0).setVisibility(View.VISIBLE);
        radioGroups.get(0).setOnCheckedChangeListener(new FadeOnCheckedListener());

        RelativeLayout iterate = findViewById(R.id.ThirdQuestionnaireFirstContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.ThirdQuestionnaireSecondItemRadioGroupID));
        radioGroups.get(1).setVisibility(View.GONE);
        radioGroups.get(1).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.ThirdQuestionnaireSecondContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.ThirdQuestionnaireThirdItemRadioGroupID));
        radioGroups.get(2).setVisibility(View.GONE);
        radioGroups.get(2).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.ThirdQuestionnaireThirdContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.ThirdQuestionnaireFourthItemRadioGroupID));
        radioGroups.get(3).setVisibility(View.GONE);
        radioGroups.get(3).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.ThirdQuestionnaireFourthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.ThirdQuestionnaireFifthItemRadioGroupID));
        radioGroups.get(4).setVisibility(View.GONE);
        radioGroups.get(4).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.ThirdQuestionnaireFifthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.ThirdQuestionnaireSixthItemRadioGroupID));
        radioGroups.get(5).setVisibility(View.GONE);
        radioGroups.get(5).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.ThirdQuestionnaireSixthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.ThirdQuestionnaireSeventhItemRadioGroupID));
        radioGroups.get(6).setVisibility(View.GONE);
        radioGroups.get(6).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.ThirdQuestionnaireSeventhContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.ThirdQuestionnaireEighthItemRadioGroupID));
        radioGroups.get(7).setVisibility(View.GONE);
        radioGroups.get(7).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.ThirdQuestionnaireEighthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.ThirdQuestionnaireNinethItemRadioGroupID));
        radioGroups.get(8).setVisibility(View.GONE);
        radioGroups.get(8).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.ThirdQuestionnaireNinethContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.ThirdQuestionnaireTenthItemRadioGroupID));
        radioGroups.get(9).setVisibility(View.GONE);
        radioGroups.get(9).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.ThirdQuestionnaireTenthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        Button next = findViewById(R.id.ThirdQuestionnaireNextButtonID);
        next.setOnClickListener(new NextPageOnClickListener());

    }
    class NextPageOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (answered < 10 ){
                Toast.makeText(third_questionnaire_questions.this,"Please answer all questions before moving to next page.",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(third_questionnaire_questions.this,fourth_questionnaire_questions.class);
                Bundle extra = new Bundle();
                intent.putExtra("token", token);
                intent.putExtra("tokenType", tokenType);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                extra.putSerializable("object",answerForm);
                intent.putExtra("answers",extra);
                startActivity(intent);
            }

        }
    }
    class FadeOnCheckedListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            radioGroup.setVisibility(View.GONE);
            int currentLocation = 0;
            for (int y = 0; y < radioGroups.size(); y++){
                currentLocation = y + 20;
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private void checkInternet()
    {
        if (!isNetworkConnected())
        {
            androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(third_questionnaire_questions.this).create();
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





    //misc











}