package com.example.firebasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsrtActivity extends AppCompatActivity {
    EditText name,roll;
    Button button;
    Spinner spinner;

    DatabaseReference studentreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertactivity);
        name=findViewById(R.id.editname);
        roll=findViewById(R.id.editrollno);
        button=findViewById(R.id.btnok);
        spinner=findViewById(R.id.spinersub);
        studentreference= FirebaseDatabase.getInstance().getReference("Student");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             inserdata();
            }
        });
    }

    public void inserdata(){
        String sname=name.getText().toString();
        String sroll=roll.getText().toString();
        String  scours=spinner.getSelectedItem().toString();
         String id=studentreference.push().getKey();

         StudentModel studentModel=new StudentModel(sname,id,sroll,scours);
         studentreference.child(id).setValue(studentModel);
        Toast.makeText(this, "data insert", Toast.LENGTH_SHORT).show();
    }
}