package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

public class Teacher_create_homework_activity extends AppCompatActivity {
    EditText title_et, content_et;
    Button back, create;
    Intent lastI;
    int teacher_index;
    int classroom_index;
    private void goback(){
        Intent intent = new Intent(this,Teacher_ClassRoom_Management_Activity.class);
        intent.putExtra("teacher_index",teacher_index);
        startActivity(intent);
    }

    private void sendMessages() {
        int student_index;
        for (int i = 0; i < my_Data.my_class_room.get(classroom_index).get_student_num(); i++) {
            student_index = my_Data.my_class_room.get(classroom_index).getMy_student().get(i);
            my_Data.user_data.getStudent(student_index).create_Message(classroom_index, "作业通知","教师发布了新的作业，请查看");
        }
        Toast.makeText(this,"作业布置成功",Toast.LENGTH_LONG).show();
        goback();
    }

    private void createHomework(){
        String title = title_et.getText().toString();
        String content = content_et.getText().toString();
        my_Data.my_class_room.get(classroom_index).create_homework(title,content);

    }
    View.OnClickListener back_l = v -> {
        goback();
    };
    View.OnClickListener create_l = v -> {
        createHomework();
        sendMessages();
    };
    private void setListener(){
        back.setOnClickListener(back_l);
        create.setOnClickListener(create_l);
    }
    private void findViews(){
        title_et = findViewById(R.id.teacher_create_homework_title);
        content_et = findViewById(R.id.teacher_create_homework_content);
        back = findViewById(R.id.teacher_create_homework_back);
        create = findViewById(R.id.teacher_create_homework_create);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_homework);
        findViews();
        setListener();
        lastI = getIntent();
        teacher_index = lastI.getIntExtra("teacher_index",-1);
        classroom_index = lastI.getIntExtra("classroom_index",-1);
    }
}