package com.example.learningapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

public class Teacher_Activity extends AppCompatActivity {
    int Teacher_index;
    Button Edit_info;
    Intent lastIn;
    TextView name;
    View.OnClickListener E_Listener = v -> {
        Intent to_info = new Intent(this, EditInfoActivity.class);
        to_info.putExtra("type", 1);
        to_info.putExtra("teacher_index", Teacher_index);
        startActivity(to_info);
    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Edit_info = findViewById(R.id.teacher_EditInfo_button);
        name = findViewById(R.id.teacher_title);
        Edit_info.setOnClickListener(E_Listener);
        lastIn = getIntent();
        name.setText(my_Data.user_data.getTeacher(Teacher_index).getName());
        Teacher_index = lastIn.getIntExtra("teacher_index", -1);
        if (Teacher_index == -1) {
            Toast.makeText(this, "登录错误，请返回重试", Toast.LENGTH_SHORT).show();
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
        }
    }
}