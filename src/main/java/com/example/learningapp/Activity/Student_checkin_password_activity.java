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

public class Student_checkin_password_activity extends AppCompatActivity {
    Intent lastI;
    int student_I;
    int classroom_I;
    int check_I;
    EditText password;
    Button checkin,back;
    private void goback(){
        Intent intent = new Intent(this,Student_ClassRoom_CheckIn_Activity.class);
        intent.putExtra("student_index",student_I);
        intent.putExtra("classroom_index",classroom_I);
        startActivity(intent);
    }
    private void gocheckin(){
        if(my_Data.my_class_room.get(classroom_I).getCheckIn(check_I).checkIn_add_student_pwd(student_I,password.getText().toString())){
            Toast.makeText(this,"签到成功",Toast.LENGTH_SHORT).show();
            goback();
        } else {
            Toast.makeText(this,"密码错误",Toast.LENGTH_SHORT).show();
        }
    }
    View.OnClickListener checkinL = v -> {
        gocheckin();
    };
    View.OnClickListener backL = v -> {
        goback();
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_checkin_password);
        lastI = getIntent();
        student_I = lastI.getIntExtra("student_index",-1);
        classroom_I = lastI.getIntExtra("classroom_index",-1);
        check_I = lastI.getIntExtra("checkIndex",-1);
        password = findViewById(R.id.student_checkIn_password_et);
        checkin = findViewById(R.id.student_checkIn_password_check);
        back = findViewById(R.id.student_checkIn_password_back);
        checkin.setOnClickListener(checkinL);
        back.setOnClickListener(backL);
    }
}