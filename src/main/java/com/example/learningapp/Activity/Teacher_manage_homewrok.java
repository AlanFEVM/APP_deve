package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.learningapp.Data.ClassRoom.HomeworkList;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;
import com.example.learningapp.recyclerViewAdapter.ManageHomework_RV_Adapter;

import java.util.ArrayList;

public class Teacher_manage_homewrok extends AppCompatActivity implements ManageHomework_RV_Adapter.ViewHomework {
    int classroom_index;
    int teacher_index;
    RecyclerView homeworkRV;
    Button back;    //TODO backbutton
    Intent lastI;
    ArrayList<HomeworkList> homework;
    ManageHomework_RV_Adapter adapter;

    private void setRecyclerView(){
        homework = my_Data.my_class_room.get(classroom_index).getHomework();
        adapter = new ManageHomework_RV_Adapter(this,homework,this);
        homeworkRV.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teacher_manage_homewrok);
        homeworkRV = findViewById(R.id.manage_homework_recyclerView);
        back = findViewById(R.id.manage_homework_back);

        lastI = getIntent();
        classroom_index = lastI.getIntExtra("classroom_index",-1);
        teacher_index = lastI.getIntExtra("teacher_index",-1);
        setRecyclerView();
        homeworkRV.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void viewStudent(int position) {
        //TODO ViewStudentHomework
    }
}