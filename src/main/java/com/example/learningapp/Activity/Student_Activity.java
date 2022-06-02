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

public class Student_Activity extends AppCompatActivity {
    Button edit_info;
    int student_index;
    Intent lastIn;
    TextView name;

    private void findViews() {
        edit_info = findViewById(R.id.student_EditInfo_button);
        name = findViewById(R.id.student_title);
    }

    View.OnClickListener edit_listener = v -> {
        Intent to_info = new Intent(this, EditInfoActivity.class);
        to_info.putExtra("type", 0);
        to_info.putExtra("student_index", student_index);
        startActivity(to_info);
    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        findViews();
        if (student_index == -1) {
            Toast.makeText(this, "登录错误，请返回重试", Toast.LENGTH_SHORT).show();
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
        }

        name.setText("Hello " + my_Data.user_data.getStudent(student_index).getName());
        lastIn = getIntent();
        student_index = lastIn.getIntExtra("student_index", -1);
        edit_info.setOnClickListener(edit_listener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestart() {
        super.onRestart();
        name.setText("Hello " + my_Data.user_data.getStudent(student_index).getName());
    }
}