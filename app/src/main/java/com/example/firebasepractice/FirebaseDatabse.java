package com.example.firebasepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDatabse extends AppCompatActivity {
         TextView txtname;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
//        DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_databse);
        txtname=findViewById(R.id.textView);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("User");
        writedata();
        readdata();
    }
    public void readdata(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder string=snapshot.getValue(StringBuilder.class);
                txtname.append(string);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void writedata(){

        databaseReference.child("user1").child("google").child("email").setValue("rajvi@gmail.com");
        databaseReference.child("user1").child("google").child("password").setValue("246810");
        databaseReference.child("user1").child("Contact").setValue("9316261566");
        DatabaseReference reference2=databaseReference.child("user2");
        DatabaseReference reference3=databaseReference.child("user3");

//        reference1.child("username").setValue("rajvi");
////        reference1.child("useremail").child("google");
//        reference1.child("userage").setValue("21");

        reference2.child("username").setValue("Devansi");
//        reference2.child("useremail").setValue("Devansigmail.com");
        reference2.child("userage").setValue("20");

        reference3.child("username").setValue("Bansi");
//        reference3.child("userEmail").setValue("Bansi@gmail.com");
        reference3.child("userAge").setValue("19");
//        databaseReference=firebaseDatabase.getReference("User");
//        DatabaseReference reference1=databaseReference.child("rajvi");
//        String email="rajviitaliya@gmail.com";
//        databaseReference.setValue(email);
//        reference=firebaseDatabase.getReference("Student");
//        String course="B.C.A";
//        reference.setValue(course);
    }
}