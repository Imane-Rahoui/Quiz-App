package com.iman.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Score extends AppCompatActivity {

    Button logout,tryAgain;
    DonutProgress progress;
    int s;
    FirebaseFirestore db;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        logout = findViewById(R.id.scorelogout);
        tryAgain = findViewById(R.id.scoreagain);
        progress = findViewById(R.id.donut_progress);
        t = findViewById(R.id.quiz4title);
        Intent intent=getIntent();
        s=intent.getIntExtra("Score",0);
        Log.i("ENFIN LE SCORE : "," : "+s);

        //db = FirebaseFirestore.getInstance();


        progress.setDonut_progress(String.valueOf(s*100/5));
        if(s*100/5 >=50)
            t.setText("Bravo Imane");
        else
            t.setText("Ops Imane");
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Score.this,Quiz1.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Score.this,MainActivity.class));
            }
        });
    }
}