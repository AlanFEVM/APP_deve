package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    Button back;
    Intent lastI;
    ArrayList<HomeworkList> homework;
    ManageHomework_RV_Adapter adapter;

    private void setRecyclerView(){
        homework = my_Data.my_class_room.get(classroom_index).getHomeworklist();
        adapter = new ManageHomework_RV_Adapter(this,homework,this);
        homeworkRV.setAdapter(adapter);
    }
    private void viewStudentHomework(int index){
        Intent intent = new Intent(this, Teacher_manageStudentHomework_Activity.class);
        intent.putExtra("classroom_index",classroom_index);
        intent.putExtra("homework_index",index);
        intent.putExtra("teacher_index",teacher_index);
        startActivity(intent);
    }
    View.OnClickListener back_L = v -> {
        goback();
    };
    private void goback(){
        Intent intent = new Intent(this,Teacher_ClassRoom_Management_Activity.class);
        intent.putExtra("classroom_index",classroom_index);
        intent.putExtra("teacher_index",teacher_index);
        startActivity(intent);
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

        back.setOnClickListener(back_L);

    }

    @Override
    public void viewStudent(int position) {
        //position -- homeworkindex
        viewStudentHomework(position);
    }
}