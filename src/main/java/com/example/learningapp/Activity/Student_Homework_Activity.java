package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.learningapp.Data.ClassRoom.HomeworkList;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;
import com.example.learningapp.recyclerViewAdapter.StudentHomework_RV_Adapter;

import java.util.ArrayList;

public class Student_Homework_Activity extends AppCompatActivity implements StudentHomework_RV_Adapter.cardButtonListener {
    Intent lastI;
    int student_index;
    int classroom_index;
    Button back;
    RecyclerView homeworkRV;
    StudentHomework_RV_Adapter adpater;
    ArrayList<HomeworkList> rv_homework;

    private void goDohomework(int position){
        if(rv_homework.get(position).getHomeworkByStudentIndex(student_index)==null){
            rv_homework.get(position).addHomework(student_index);
        }
        if(!rv_homework.get(position).getHomeworkByStudentIndex(student_index).isCommented()){
            Intent intent = new Intent(this, Student_Classroom_doHomework_Activity.class);
            intent.putExtra("homework_index",position);
            intent.putExtra("student_index",student_index);
            intent.putExtra("classroom_index",classroom_index);
            startActivity(intent);
        } else{
            Toast.makeText(this,"主人，作业已被批给，无法编辑",Toast.LENGTH_LONG).show();
        }
    }

    private void goViewHomework(int position){
        Intent intent = new Intent(this, Student_Classroom_viewHomework_Activity.class);
        intent.putExtra("homework_index",position);
        intent.putExtra("student_index",student_index);
        intent.putExtra("classroom_index",classroom_index);
        startActivity(intent);
    }
    private void goback(){
        Intent intent = new Intent(this,Student_ClassRoom_Activity.class);
        intent.putExtra("student_index",student_index);
        intent.putExtra("classroom_index",classroom_index);
        startActivity(intent);
    }
    View.OnClickListener back_L = v -> {
        goback();
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homework);
        lastI = getIntent();
        student_index = lastI.getIntExtra("student_index",-1);
        classroom_index = lastI.getIntExtra("classroom_index",-1);
        back = findViewById(R.id.student_homework_back_btn);
        homeworkRV = findViewById(R.id.student_homework_recyclerView);
        rv_homework = my_Data.my_class_room.get(classroom_index).getHomeworklist();
        adpater = new StudentHomework_RV_Adapter(this,rv_homework,this);
        homeworkRV.setAdapter(adpater);
        homeworkRV.setLayoutManager(new LinearLayoutManager(this));
        back.setOnClickListener(back_L);
    }

    @Override
    public void dowork(int position) {
        goDohomework(position);
    }
 
    @Override
    public void viewComment(int position) {
        if(rv_homework.get(position).getHomeworkByStudentIndex(student_index) != null){
            if(rv_homework.get(position).getHomeworkByStudentIndex(student_index).isSubmited()){
                if(rv_homework.get(position).getHomeworkByStudentIndex(student_index).isCommented()){
                    goViewHomework(position);
                } else{
                    Toast.makeText(this,"主人，你的作业还没有批改哦",Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this,"主人，你还没有提交作业哦",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"主人，你的还没有完成作业哦",Toast.LENGTH_LONG).show();
        }

    }
}