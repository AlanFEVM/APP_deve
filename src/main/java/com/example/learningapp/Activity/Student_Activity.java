package com.example.learningapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.Data.DataBase;
import com.example.learningapp.R;

public class Student_Activity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Intent lastIn = getIntent();
        DataBase my_database = (DataBase) lastIn.getSerializableExtra("database");
        int StudentNumber = lastIn.getIntExtra("StudentNum", -1);
        if (StudentNumber == -1) {
            Toast.makeText(this, "登录错误，请返回重试", Toast.LENGTH_SHORT).show();
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
        }
        TextView account = findViewById(R.id.s_d_account);
        TextView age = findViewById(R.id.s_d_age);
        TextView name = findViewById(R.id.s_d_name);
        TextView code = findViewById(R.id.s_d_student_code);
        String s_account = my_database.getStudent(StudentNumber).getAccountData().getAccount_name();
        int s_age = my_database.getStudent(StudentNumber).getAge();
        String s_name = my_database.getStudent(StudentNumber).getName();
        String s_code = my_database.getStudent(StudentNumber).getStudent_code();
        account.setText("账号:" + s_account);
        age.setText("年龄:" + Integer.toString(s_age));
        name.setText("姓名:" + s_name);
        code.setText("学号:" + s_code);
    }
}