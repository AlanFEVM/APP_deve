package com.example.learningapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.Data.DataBase;
import com.example.learningapp.R;

public class Teacher_Activity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        Intent lastIn = getIntent();
        DataBase my_database = (DataBase) lastIn.getSerializableExtra("database");
        int TeacherNumber = lastIn.getIntExtra("TeacherNum", -1);

        if (TeacherNumber == -1) {
            Toast.makeText(this, "登录错误，请返回重试", Toast.LENGTH_SHORT).show();
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
        }

        TextView account = findViewById(R.id.t_d_account);
        TextView age = findViewById(R.id.t_d_age);
        TextView name = findViewById(R.id.t_d_name);
        TextView code = findViewById(R.id.t_d_teacher_code);

        String t_account = my_database.getTeacher(TeacherNumber).getAccountData().getAccount_name();
        int t_age = my_database.getTeacher(TeacherNumber).getAge();
        String t_name = my_database.getTeacher(TeacherNumber).getName();
        String t_code = my_database.getTeacher(TeacherNumber).getTeacher_code();

        account.setText("账号:" + t_account);
        age.setText("年龄：" + Integer.toString(t_age));
        name.setText("姓名:" + t_name);
        code.setText("教师编号:" + t_code);

    }
}