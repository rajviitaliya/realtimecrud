package com.example.firebasepractice;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StudentAdapter extends ArrayAdapter {

    public Activity Scontext;

    List<StudentModel> studentModelList;
      StudentAdapter(Activity scontext,List<StudentModel>studentModelList){
          super(scontext,R.layout.list,studentModelList);
          this.Scontext=scontext;
          this.studentModelList=studentModelList;

      }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=Scontext.getLayoutInflater();
        View view=inflater.inflate(R.layout.list,null,true);

        TextView name=view.findViewById(R.id.txtname);
        TextView roll=view.findViewById(R.id.txtroll);
        TextView course=view.findViewById(R.id.txtcours);

        StudentModel studentModel=studentModelList.get(position);
        name.setText(studentModel.getName());
        roll.setText(studentModel.getRoll());
        course.setText(studentModel.getCourse());
        return view;
    }


}

