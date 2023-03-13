package com.example.learningapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.CustomViews.HideHintEditText;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

public class LoginActivity extends AppCompatActivity {
    public Button login, signup;
    public RadioButton T_login, S_login;
    public HideHintEditText account, passW;
    String my_Account;
    String password;

    View.OnClickListener l_listener = v -> Login();
    View.OnClickListener S_listener = v -> goSignup();

    private void Login() {
        Intent to_Student = new Intent(this, Student_Activity.class);
        Intent to_Teacher = new Intent(this, Teacher_Activity.class);

        my_Account = account.getText().toString();
        password = passW.getText().toString();

        if (my_Account.matches("") || password.matches("")) {
            Toast.makeText(this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!S_login.isChecked() && !T_login.isChecked()) {
            Toast.makeText(this, "请勾选教师或者用户", Toast.LENGTH_LONG).show();
        }
        if (T_login.isChecked()) {
            int t = my_Data.user_data.matchTeacher(my_Account, password);
            if (t == -1) {
                Toast.makeText(this, "账号不存在或密码错误", Toast.LENGTH_SHORT).show();
                return;
            }
            to_Teacher.putExtra("teacher_index", t);
            startActivity(to_Teacher);
        }

        if (S_login.isChecked()) {
            int s = my_Data.user_data.matchStudent(my_Account, password);
            if (s == -1) {
                Toast.makeText(this, "账号不存在或密码错误", Toast.LENGTH_SHORT).show();
                return;
            }
            to_Student.putExtra("student_index", s);
            startActivity(to_Student);
        }
    }

    public void goSignup() {
        Intent my_intent = new Intent(this, Sign_Up_Activity.class);
        startActivity(my_intent);
    }

    public void find_view() {
        login = findViewById(R.id.logInButton);
        signup = findViewById(R.id.LoginButtonSignup);
        T_login = findViewById(R.id.Teacher_Login);
        S_login = findViewById(R.id.Student_Login);
        account = findViewById(R.id.Account);
        passW = findViewById(R.id.Password);
    }
    public void setListener() {
        signup.setOnClickListener(S_listener);
        login.setOnClickListener(l_listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //首先创建我们的数据库，这里的数据库用的是一个静态类
        if (my_Data.user_data == null) {
            my_Data.initial_data_base();
        }
        find_view();
        setListener();
    }
}