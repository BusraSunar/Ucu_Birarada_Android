package com.example.Ucu_Birarada_Android.StaticAnket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Ucu_Birarada_Android.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class first_questionnaire_questions extends AppCompatActivity {

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
        setContentView(R.layout.activity_first_questionnaire_questions);
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

        System.out.println("ANKET 1: "+ email + " " + password);

        //Buradan -- yaz
    }


    //Button metotları




    //private metotlar
    private void init()
    {
        answerForm = new ArrayList<>();
        HashMap<String,String> answer = new HashMap<>();
        answer.put("questionBody","I am the life of the party.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I feel little concern for others.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I am always prepared.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I get stressed out easily.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I have a rich vocabulary.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I don't talk a lot.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I am interested in people.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I leave my belongings around.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I am relaxed most of the time.");
        answer.put("answer","");
        answerForm.add(answer);

        answer = new HashMap<>();
        answer.put("questionBody","I have difficulty understanding abstract ideas.");
        answer.put("answer","");
        answerForm.add(answer);

        containers = new ArrayList<>();
        radioGroups = new ArrayList<RadioGroup>(25);
        radioGroups.add(findViewById(R.id.FirstQuestionnaireFirstItemRadioGroupID));

        radioGroups.get(0).setVisibility(View.VISIBLE);
        radioGroups.get(0).setOnCheckedChangeListener(new FadeOnCheckedListener());

        RelativeLayout iterate = findViewById(R.id.FirstQuestionnaireFirstContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FirstQuestionnaireSecondItemRadioGroupID));
        radioGroups.get(1).setVisibility(View.GONE);
        radioGroups.get(1).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FirstQuestionnaireSecondContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FirstQuestionnaireThirdItemRadioGroupID));
        radioGroups.get(2).setVisibility(View.GONE);
        radioGroups.get(2).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FirstQuestionnaireThirdContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FirstQuestionnaireFourthItemRadioGroupID));
        radioGroups.get(3).setVisibility(View.GONE);
        radioGroups.get(3).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FirstQuestionnaireFourthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FirstQuestionnaireFifthItemRadioGroupID));
        radioGroups.get(4).setVisibility(View.GONE);
        radioGroups.get(4).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FirstQuestionnaireFifthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FirstQuestionnaireSixthItemRadioGroupID));
        radioGroups.get(5).setVisibility(View.GONE);
        radioGroups.get(5).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FirstQuestionnaireSixthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FirstQuestionnaireSeventhItemRadioGroupID));
        radioGroups.get(6).setVisibility(View.GONE);
        radioGroups.get(6).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FirstQuestionnaireSeventhContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FirstQuestionnaireEighthItemRadioGroupID));
        radioGroups.get(7).setVisibility(View.GONE);
        radioGroups.get(7).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FirstQuestionnaireEighthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FirstQuestionnaireNinethItemRadioGroupID));
        radioGroups.get(8).setVisibility(View.GONE);
        radioGroups.get(8).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FirstQuestionnaireNinethContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        radioGroups.add(findViewById(R.id.FirstQuestionnaireTenthItemRadioGroupID));
        radioGroups.get(9).setVisibility(View.GONE);
        radioGroups.get(9).setOnCheckedChangeListener(new FadeOnCheckedListener());

        iterate = findViewById(R.id.FirstQuestionnaireTenthContainerID);
        iterate.setOnClickListener(new AppearOnClickListener());
        containers.add(iterate);

        Button next = findViewById(R.id.FirstQuestionnaireNextButtonID);
        next.setOnClickListener(new NextPageOnClickListener());

    }
    class NextPageOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (answered < 10 ){
                Toast.makeText(first_questionnaire_questions.this,"Please answer all questions before moving to next page.",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(first_questionnaire_questions.this,second_questionnaire_questions.class);
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
            for (int y = 0; y < radioGroups.size(); y++){
                if (radioGroups.get(y).equals(radioGroup)){
                    if (answerForm.get(y).get("answer").equals("")){
                        answered++;
                    }
                    switch (i%5){
                        case 0:
                            answerForm.get(y).put("answer","Completely Agree");
                            break;
                        case 1:
                            answerForm.get(y).put("answer","Agree");
                            break;
                        case 2:
                            answerForm.get(y).put("answer","Nor Agree Nor Disagree");
                            break;
                        case 3:
                            answerForm.get(y).put("answer","Disagree");
                            break;
                        case 4:
                            answerForm.get(y).put("answer","Completely Disagree");
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