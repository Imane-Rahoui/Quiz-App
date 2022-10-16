package com.iman.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //  declaration
    EditText email,password;
    TextView register;
    Button signin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  le lien entre le layout et le code java //  default meth

        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        //  recuperation des contenues

        email=findViewById(R.id.mainemail);
        password=findViewById(R.id.mainpassword);
        signin=findViewById(R.id.mainsignin);
        register=findViewById(R.id.mainregister);

        //  gerer l'env onclick

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  le code qui va etre exec au moment du click

                //  verification
/*                if (email.getText().toString().trim().equals("i") && password.getText().toString().trim().equals("i")) {
                    //  creer le lien pour se diriger vers une autre activité
                    Intent intent = new Intent(MainActivity.this, Quiz1.class);

                    //  se diriger vers l activity
                    startActivity(intent);
                } else {
                    //  creation d'un objet de type Toast // pop up
                    Toast.makeText(getApplicationContext(), "email or password isn't correct", Toast.LENGTH_LONG).show();
                }*/

                String emailS = email.getText().toString();
                String passwordS = password.getText().toString();

                if (emailS.isEmpty() && emailS.isEmpty()){
                    Toast.makeText(MainActivity.this, "Email and password cannot Empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.signInWithEmailAndPassword(emailS,passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                                startActivity(new Intent(MainActivity.this,Quiz1.class));
                            else
                                Toast.makeText(MainActivity.this,"Wrong Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,register.class));
            }
        });
    }
}