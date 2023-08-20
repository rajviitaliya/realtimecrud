package com.example.firebasepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
    EditText sname, sroll;
    Spinner spinner;
    Button buttondelete, buttonupdate;
    ListView listView;
    List<StudentModel> ModelList;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        listView = findViewById(R.id.listdata);
        ModelList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Student");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    StudentModel studentModel = dataSnapshot.getValue(StudentModel.class);
                    ModelList.add(studentModel);
                }
                StudentAdapter studentAdapter = new StudentAdapter(ReadActivity.this, ModelList);
                listView.setAdapter(studentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                StudentModel studentModel = ModelList.get(i);
                showdata(studentModel.getId(), studentModel.getName());


                return false;
            }
        });

    }

    //
    public void showdata(String id, String name) {
//        final Dialog dialog = new Dialog(ReadActivity.this);
//        dialog.setContentView(R.layout.dilouge);
//        dialog.getWindow().setLayout(1100, 1300);
//        dialog.show();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dilouge, null);
        builder.setView(view);
        sname = view.findViewById(R.id.editname);
        sroll = view.findViewById(R.id.editrollno);
        spinner = view.findViewById(R.id.spinersub);
        buttondelete = view.findViewById(R.id.btndelete);
        buttonupdate = view.findViewById(R.id.btnupdate);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletedata(id);
                ModelList.remove(id);
                alertDialog.dismiss();
            }

        });
        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=sname.getText().toString();
                String Roll=sroll.getText().toString();
                String Course=spinner.getSelectedItem().toString();
              updatedata(id,Name,Roll,Course);
                Toast.makeText(ReadActivity.this, "data update", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    public void deletedata(String id) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Student").child(id);
        Task<Void> task = databaseReference.removeValue();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ReadActivity.this, "not delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updatedata(String id, String name, String roll, String course) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Student").child(id);
        StudentModel studentModel = new StudentModel(name,id, roll, course);
        databaseReference.setValue(studentModel);
    }
}