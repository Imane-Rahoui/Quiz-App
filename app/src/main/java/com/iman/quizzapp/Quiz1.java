package com.iman.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Quiz1 extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb;
    Button next;
    int score=0;
    String correctAnswer="Oui";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        rg=findViewById(R.id.quiz1grp);
        next=findViewById(R.id.quiz1next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rg.getCheckedRadioButtonId()==-1)
                    Toast.makeText(Quiz1.this, "You should choose", Toast.LENGTH_LONG).show();
                else {
                    rb=findViewById(rg.getCheckedRadioButtonId());
                    if(rb.getText().toString().equals(correctAnswer))
                    {
                        score++;
                    }
                    Log.i("test"," : "+score);
                    Intent intent = new Intent(Quiz1.this, Quiz2.class);
                    intent.putExtra("Score",score);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}