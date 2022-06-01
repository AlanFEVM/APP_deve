package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.learningapp.Data.my_Data;

import com.example.learningapp.R;

public class EditInfoActivity extends AppCompatActivity {
    Button edit_info_button;
    Button change_password_button;
    LinearLayout edit_info_layout;
    LinearLayout change_password_layout;
    TextView title;
    TextView info_account;
    TextView info_name;
    TextView info_gender;
    TextView info_age;
    TextView info_code;
    int personIndex = -1;
    int type = -1;
    View.OnClickListener edit_info_Listener = v -> {
        edit_info_layout.setVisibility(View.VISIBLE);
        change_password_layout.setVisibility(View.GONE);
    };
    View.OnClickListener change_pwd_Listener = v -> {
        edit_info_layout.setVisibility(View.GONE);
        change_password_layout.setVisibility(View.VISIBLE);
    };

    private void setListener() {
        edit_info_button.setOnClickListener(edit_info_Listener);
        change_password_button.setOnClickListener(change_pwd_Listener);
    }

    private void get_Data() {
        Intent lastIntent = getIntent();
        type = lastIntent.getIntExtra("type", -1);
        if (type == 0) {
            personIndex = lastIntent.getIntExtra("student_index", -1);
        }
        if (type == 1) {
            personIndex = lastIntent.getIntExtra("teacher_index", -1);
        }
    }

    private void findViews() {
        edit_info_button = findViewById(R.id.info_EditInfo_button);
        change_password_button = findViewById(R.id.info_Changepwd_button);
        edit_info_layout = findViewById(R.id.info_EditInfo_layout);
        change_password_layout = findViewById(R.id.info_Changepwd_layout);
        title = findViewById(R.id.info_title);
        info_account = findViewById(R.id.info_account);
        info_name = findViewById(R.id.info_name);
        info_gender = findViewById(R.id.info_gender);
        info_age = findViewById(R.id.info_age);
        info_code = findViewById(R.id.info_code);
    }

    @SuppressLint("SetTextI18n")
    private void set_info() {
        //student setup
        if (type == 0) {
            title.setText("学生信息管理");
            info_account.setText("用户名: " + my_Data.user_data.getStudent(personIndex).getAccountData().getAccount_name());
            info_name.setText("姓名: " + my_Data.user_data.getStudent(personIndex).getName());
            info_age.setText("年龄: " + my_Data.user_data.getStudent(personIndex).getAge());
            info_gender.setText("性别: " + my_Data.user_data.getStudent(personIndex).getGender());
            info_code.setText("学号: " + my_Data.user_data.getStudent(personIndex).getStudent_code());
        }
        //teacher setup
        if (type == 1) {
            title.setText("教师信息管理");
            info_account.setText("用户名: " + my_Data.user_data.getTeacher(personIndex).getAccountData().getAccount_name());
            info_name.setText("姓名: " + my_Data.user_data.getTeacher(personIndex).getName());
            info_age.setText("年龄: " + my_Data.user_data.getTeacher(personIndex).getAge());
            info_gender.setText("性别: " + my_Data.user_data.getTeacher(personIndex).getGender());
            info_code.setText("教师编号: " + my_Data.user_data.getTeacher(personIndex).getTeacher_code());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_card);
        findViews();
        setListener();
        get_Data();
        set_info();
    }
}