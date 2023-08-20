package com.example.firebasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //    TextView name,email;
    Button insert, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert = findViewById(R.id.btninsert);
        view = findViewById(R.id.btnview);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(MainActivity.this, InsrtActivity.class);
              startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         Intent intent=new Intent(MainActivity.this,ReadActivity.class);
         startActivity(intent);
            }
        });


//        name=findViewById(R.id.txtname);
//        email=findViewById(R.id.txtemail);
//        logout=findViewById(R.id.btnlogout);
//        GoogleSignInAccount googleSignInAccount= GoogleSignIn.getLastSignedInAccount(MainActivity.this);
//        if (googleSignInAccount!= null){
//            name.setText(googleSignInAccount.getDisplayName());
//            email.setText(googleSignInAccount.getEmail());
//        }
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent=new Intent(MainActivity.this,login.class);
//                startActivity(intent);
//            }
//        });
//    }
    }
}