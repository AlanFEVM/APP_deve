package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learningapp.Data.ClassRoom.StudentHomework;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;
import com.example.learningapp.recyclerViewAdapter.TeacherManageStudentHomework_RV_Adapter;

import java.util.ArrayList;

public class Teacher_manageStudentHomework_Activity extends AppCompatActivity implements TeacherManageStudentHomework_RV_Adapter.btnListener {
    Intent lastI;
    int classroom_index;
    int teacher_index;
    int homework_index;
    TeacherManageStudentHomework_RV_Adapter adapter;
    Button back;
    ArrayList<StudentHomework> studentHomeworks;
    RecyclerView student_homework_recyclerview;

    private void goback(){
        Intent intent = new Intent(this,Teacher_manage_homewrok.class);
        intent.putExtra("teacher_index",teacher_index);
        intent.putExtra("classroom_index",classroom_index);
        startActivity(intent);
    }
    View.OnClickListener back_L = v -> {
        goback();
    };

    private void goViewHomework(int student_index){
        Intent intent = new Intent(this,Teacher_viewStudentHomework_Activity.class);
        intent.putExtra("teacher_index",teacher_index);
        intent.putExtra("classroom_index",classroom_index);
        intent.putExtra("student_index",student_index);
        intent.putExtra("homework_index",homework_index);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_manage_student_homework);
        lastI = getIntent();
        classroom_index = lastI.getIntExtra("classroom_index",-1);
        teacher_index = lastI.getIntExtra("teacher_index",-1);
        homework_index = lastI.getIntExtra("homework_index",-1);
        studentHomeworks = my_Data.my_class_room.get(classroom_index).getHomeworklist().get(homework_index).getHomework();
        back = findViewById(R.id.teacher_manage_homework_back);
        student_homework_recyclerview = findViewById(R.id.teacher_manage_homework_rv);
        adapter = new TeacherManageStudentHomework_RV_Adapter(this,studentHomeworks,this);
        student_homework_recyclerview.setAdapter(adapter);
        student_homework_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        back.setOnClickListener(back_L);
    }

    @Override
    public void onButtonClick(int position) {
        int student_index;
        student_index = studentHomeworks.get(position).getStudent_index();
        goViewHomework(student_index);
    }
}