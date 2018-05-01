package com.example.gabrielsaruhashi.ramp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gabrielsaruhashi.ramp.CategoryView;
import com.example.gabrielsaruhashi.ramp.R;

/**
 * Created by tylershen on 3/29/18.
 */

public class QualificationsQuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qualifications_questions);

        final Context context = this;
        Button btAns1 = (Button) findViewById(R.id.btAns1);
        btAns1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CategoryView.class);
                startActivityForResult(myIntent, 0);
                ((Activity) context).overridePendingTransition(0, 0);
            }}
        );

        Button btAns2 = (Button) findViewById(R.id.btAns3);
        btAns2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CategoryView.class);
                startActivityForResult(myIntent, 0);
                ((Activity) context).overridePendingTransition(0, 0);
            }}
        );

        Button btAns3 = (Button) findViewById(R.id.btAns2);
        btAns3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CategoryView.class);
                startActivityForResult(myIntent, 0);
                ((Activity) context).overridePendingTransition(0, 0);
            }}
        );
    }
}
