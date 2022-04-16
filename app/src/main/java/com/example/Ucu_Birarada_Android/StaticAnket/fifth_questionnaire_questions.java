package com.example.Ucu_Birarada_Android.StaticAnket;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.Ucu_Birarada_Android.HomeActivity;
import com.example.Ucu_Birarada_Android.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class fifth_questionnaire_questions extends AppCompatActivity {

    private ArrayList<String> answers;
    private ArrayList<RadioGroup> radioGroups;
    private ArrayList<RelativeLayout> containers;

    private String token;
    private String tokenType;
    private String email;
    private String password;
    private final String URL = "http://10.2.37.139:8080/question/submitAnswers";
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extra = getIntent().getBundleExtra("answers");
        answers = (ArrayList<String>) extra.getSerializable("object");
        setContentView(R.layout.activity_second_questionnaire_questions);
        this.init();
        context = getApplicationContext();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            tokenType = intent.getStringExtra("tokenType");
            token = intent.getStringExtra("token");
            email = intent.getStringExtra("email");
            password = intent.getStringExtra("password");
        }


        //Buradan -- yaz
        System.out.println("ANKET 5: " + email + " " + password);
    }


    //private metotlar
    private void init() {
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

    class NextPageOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {


            System.out.println("BURADAYIM");
            RequestQueue queue = Volley.newRequestQueue(context);
            // Post params to be sent to the server
            HashMap<String, ArrayList<String>> params = new HashMap<>();
            params.put("payload", answers);

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
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());
                }
            }) {

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
            queue.add(req);

            Intent intent = new Intent(fifth_questionnaire_questions.this, HomeActivity.class);
            Bundle extra = new Bundle();
            extra.putSerializable("object", answers);
            intent.putExtra("token", token);
            intent.putExtra("tokenType", tokenType);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            startActivity(intent);


        }
    }


    class FadeOnCheckedListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            radioGroup.setVisibility(View.GONE);
            for (int y = 0; y < radioGroups.size(); y++) {
                if (y != radioGroups.size() - 1 && radioGroups.get(y).equals(radioGroup)) {
                    radioGroups.get(y + 1).setVisibility(View.VISIBLE);
                }
            }
        }
    }

    class AppearOnClickListener implements RelativeLayout.OnClickListener {

        @Override
        public void onClick(View view) {
            RelativeLayout layout = (RelativeLayout) view;
            for (RadioGroup fade : radioGroups) {
                fade.setVisibility(View.GONE);
            }
            RadioGroup toAppear = (RadioGroup) layout.getChildAt(1);
            toAppear.setVisibility(View.VISIBLE);

        }
    }
}


//database metotları






//misc











