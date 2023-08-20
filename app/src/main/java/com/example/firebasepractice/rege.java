package com.example.firebasepractice;

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
import com.google.firebase.auth.FirebaseUser;

public class rege extends AppCompatActivity {
   EditText name,email,pass;
   Button button;
   TextView textView;
   FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rege);
        name=findViewById(R.id.regename);
        email=findViewById(R.id.regermail);
        pass=findViewById(R.id.regepass);
        button=findViewById(R.id.btnrege);
        textView=findViewById(R.id.txt2);
        firebaseAuth=FirebaseAuth.getInstance();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(rege.this, login.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("")&&pass.getText().toString().equals("")){
                    Toast.makeText(rege.this, "No Match", Toast.LENGTH_SHORT).show();
                }else {
                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(rege.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                            Toast.makeText(rege.this, ""+firebaseUser, Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(rege.this, login.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });




    }
}