package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapp.Data.ClassRoom.HomeworkList;
import com.example.learningapp.Data.ClassRoom.StudentHomework;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

import java.util.ArrayList;

public class Student_Classroom_doHomework_Activity extends AppCompatActivity {
    int homeworkindex;
    int student_index;
    int classroom_index;
    StudentHomework my_Homework;
    ArrayList<HomeworkList> homeworkLists;
    Intent lastI;
    TextView title;
    TextView content;
    EditText answer;
    Button back, submit;

    private void goback(){
        if(!answer.getText().toString().matches("")){
            my_Homework.set_buffer_answer(answer.getText().toString());
            Toast.makeText(this,"主人，答案自动保存啦",Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this,Student_Homework_Activity.class);
        intent.putExtra("student_index",student_index);
        intent.putExtra("classroom_index",classroom_index);
        startActivity(intent);
    }

    private void dosubmit(){
        if(!answer.getText().toString().matches("")){
            my_Homework.set_answer(answer.getText().toString());
            my_Homework.set_buffer_answer(answer.getText().toString());
            my_Homework.setSubmited();
            Toast.makeText(this,"好哒，主人，包在我身上",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"主人，请完成题目哦",Toast.LENGTH_LONG).show();
        }
    }

    View.OnClickListener back_L = v -> {
        goback();
    };

    View.OnClickListener submit_L = v -> {
        dosubmit();
    };

    private void catchData(){
        title.setText(homeworkLists.get(homeworkindex).getTitle());
        content.setText(homeworkLists.get(homeworkindex).getContent());
    }

    private void findViews(){
        title = findViewById(R.id.student_dohomework_title);
        content =findViewById(R.id.student_dohomework_content);
        answer = findViewById(R.id.student_dohomework_answer);
        back = findViewById(R.id.student_dohomework_back);
        submit = findViewById(R.id.student_dohomework_submit);
    }
    private void setUpHomework(){
        //检测学生是否已经创建了作业，没有的话就创建，
        my_Homework = homeworkLists.get(homeworkindex).getHomeworkByStudentIndex(student_index);
        if(my_Homework != null){
            if(!my_Homework.get_buffer_answer().matches("")){
                answer.setText(my_Homework.get_buffer_answer());
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuent_classroom_do_homwork);
        lastI = getIntent();
        homeworkindex = lastI.getIntExtra("homework_index",-1);
        student_index = lastI.getIntExtra("student_index",-1);
        classroom_index = lastI.getIntExtra("classroom_index",-1);
        findViews();
        homeworkLists = my_Data.my_class_room.get(classroom_index).getHomeworklist();
        setUpHomework();
        catchData();
        back.setOnClickListener(back_L);
        submit.setOnClickListener(submit_L);
    }
}