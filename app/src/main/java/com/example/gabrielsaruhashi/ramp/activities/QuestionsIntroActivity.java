package com.example.gabrielsaruhashi.ramp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gabrielsaruhashi.ramp.R;

public class QuestionsIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_intro);

        final Context context = this;
        Button next = (Button) findViewById(R.id.btOkay);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), QualificationsQuestionsActivity.class);
                startActivityForResult(myIntent, 0);
                ((Activity) context).overridePendingTransition(0, 0);
            }
        });
    }
}
