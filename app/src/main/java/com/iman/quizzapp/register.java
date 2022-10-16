package com.iman.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class register extends AppCompatActivity {

    EditText regname,regemail,regpassword1,regpassword2;
    Button regregister;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseFirestore.getInstance();
        regname=findViewById(R.id.registername);
        regpassword1=findViewById(R.id.registerpassword1);
        regpassword2=findViewById(R.id.registerpassword2);
        regemail=findViewById(R.id.registeremail);
        regregister=findViewById(R.id.registerregister);

        mAuth = FirebaseAuth.getInstance();
        regregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(regemail.getText().toString()) || TextUtils.isEmpty(regpassword1.getText().toString())) {

                    Toast.makeText(register.this, "email / password required", Toast.LENGTH_LONG).show();
                    return;
                }
                Log.i("allez ************** :",regname.getText().toString());
                if(regpassword1.getText().toString().equals(regpassword2.getText().toString())){

                    {

                        String name = regname.getText().toString();
                        Map<String,Object> user = new HashMap<>();
                        user.put("name",name);
                        db.collection("users").document("names")
                                .set(user);

                    }

                    mAuth.createUserWithEmailAndPassword(regemail.getText().toString(),regpassword1.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(register.this,"User registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(register.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(register.this, "Registration Error" + Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(register.this,"Password and Confirm Password are not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}