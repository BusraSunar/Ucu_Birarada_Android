package com.example.Ucu_Birarada_Android.StaticAnket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.Ucu_Birarada_Android.R;

import java.util.ArrayList;
import java.util.HashMap;

public class fourth_questionnaire_questions extends AppCompatActivity {

    private ArrayList<RadioGroup> radioGroups;
    private ArrayList<RelativeLayout> containers;
    private ArrayList<HashMap<String,String>> answerForm;
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
        answerForm = (ArrayList<HashMap<String,String>>) extra.getSerializable("object");
        setContentView(R.layout.activity_fourth_questionnaire_questions);
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


        //Buradan -- yaz
        System.out.println("ANKET 4: "+ email + " " + password);

    }


    //Button metotları




    //private metotlar
    private void init()
    {

        HashMap<String,String> answer = new HashMap<>();
        answer.put("questionBody","I talk to a lot of different people at parties.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I am not really interested in others.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I like order.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I change my mood a lot.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I am quick to understand things.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I don't like to draw attention to myself.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I take time out for others.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I shirk my duties.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I have frequent mood swings.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I use difficult words.");
        answer.put("answer","");
        answerForm.add(answer);


        containers = new ArrayList<>();
        radioGroups = new ArrayList<RadioGroup>(25);
        radioGroups.add(findViewById(R.id.FourthQuestionnaireFirstItemRadioGroupID));

        radioGroups.get(0).setVisibility(View.VISIBLE);
        radioGroups.get(0).setOnCheckedChangeListener(new FadeOnCheckedListener());

        RelativeLayout iterate = findViewById(R.id.FourthQuestionnaireFirstContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FourthQuestionnaireSecondItemRadioGroupID));
        radioGroups.get(1).setVisibility(View.GONE);
        radioGroups.get(1).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FourthQuestionnaireSecondContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FourthQuestionnaireThirdItemRadioGroupID));
        radioGroups.get(2).setVisibility(View.GONE);
        radioGroups.get(2).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FourthQuestionnaireThirdContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FourthQuestionnaireFourthItemRadioGroupID));
        radioGroups.get(3).setVisibility(View.GONE);
        radioGroups.get(3).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FourthQuestionnaireFourthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FourthQuestionnaireFifthItemRadioGroupID));
        radioGroups.get(4).setVisibility(View.GONE);
        radioGroups.get(4).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FourthQuestionnaireFifthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FourthQuestionnaireSixthItemRadioGroupID));
        radioGroups.get(5).setVisibility(View.GONE);
        radioGroups.get(5).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FourthQuestionnaireSixthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FourthQuestionnaireSeventhItemRadioGroupID));
        radioGroups.get(6).setVisibility(View.GONE);
        radioGroups.get(6).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FourthQuestionnaireSeventhContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FourthQuestionnaireEighthItemRadioGroupID));
        radioGroups.get(7).setVisibility(View.GONE);
        radioGroups.get(7).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FourthQuestionnaireEighthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FourthQuestionnaireNinethItemRadioGroupID));
        radioGroups.get(8).setVisibility(View.GONE);
        radioGroups.get(8).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FourthQuestionnaireNinethContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FourthQuestionnaireTenthItemRadioGroupID));
        radioGroups.get(9).setVisibility(View.GONE);
        radioGroups.get(9).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FourthQuestionnaireTenthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        Button next = findViewById(R.id.FourthQuestionnaireNextButtonID);
        next.setOnClickListener(new NextPageOnClickListener());

    }
    class NextPageOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (answered < 10 ){
                Toast.makeText(fourth_questionnaire_questions.this,"Please answer all questions before moving to next page.",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(fourth_questionnaire_questions.this,fifth_questionnaire_questions.class);
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
            int currentLocation = 0;
            radioGroup.setVisibility(View.GONE);
            for (int y = 0; y < radioGroups.size(); y++){
                currentLocation = y + 30;
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











}