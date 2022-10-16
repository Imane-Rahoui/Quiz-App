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

public class Quiz4 extends AppCompatActivity {

    Button next;
    RadioGroup rg;
    RadioButton rb;
    String correctAnswer="Oui";
    int s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz4);
        rg=findViewById(R.id.quiz4grp);
        next=findViewById(R.id.quiz4next);
        Intent intent=getIntent();
        s=intent.getIntExtra("Score",0);
        Log.i("LE SCORE EST RECUPERE "," : "+s);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quiz4.this,Quiz5.class));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rg.getCheckedRadioButtonId()==-1)
                    Toast.makeText(Quiz4.this, "You should choose", Toast.LENGTH_LONG).show();
                else {
                    rb=findViewById(rg.getCheckedRadioButtonId());
                    if(rb.getText().toString().equals(correctAnswer))
                    {
                        s++;
                    }
                    Log.i("test"," : "+s);
                    Intent intent = new Intent(Quiz4.this, Quiz5.class);
                    intent.putExtra("Score",s);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
