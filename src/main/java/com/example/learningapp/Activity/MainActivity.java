package com.example.learningapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.Data.AccountData;
import com.example.learningapp.Data.DataBase;
import com.example.learningapp.Data.Student;
import com.example.learningapp.Data.Teacher;
import com.example.learningapp.R;

public class MainActivity extends AppCompatActivity {
    public Button login;
    public Button signup;
    public RadioButton T_login;
    public RadioButton S_login;
    public EditText account;
    public EditText passW;
    public DataBase my_db = new DataBase();

    View.OnClickListener l_listener = v -> goActivity();
    View.OnClickListener S_listener = v -> goSignup();

    private void goActivity() {
        Intent to_Student = new Intent(this, Student_Activity.class);
        Intent to_Teacher = new Intent(this, Teacher_Activity.class);
        account = findViewById(R.id.Account);
        passW = findViewById(R.id.Password);
        String my_Account = account.getText().toString();
        String password = passW.getText().toString();

        if (my_Account.matches("") || password.matches("")) {
            Toast.makeText(this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (T_login.isChecked()) {
            int t = my_db.findTeacher(my_Account, password);
            if (t == -1) {
                Toast.makeText(this, "账号不存在或密码错误", Toast.LENGTH_SHORT).show();
                return;
            }
            to_Teacher.putExtra("TeacherNum", t);
            to_Teacher.putExtra("database", my_db);
            startActivity(to_Teacher);
        }

        if (S_login.isChecked()) {
            int s = my_db.findStudent(my_Account, password);
            if (s == -1) {
                Toast.makeText(this, "账号不存在或密码错误", Toast.LENGTH_SHORT).show();
                return;
            }
            to_Student.putExtra("StudentNum", s);
            to_Student.putExtra("database", my_db);
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
    }

    public void setListener() {
        signup.setOnClickListener(S_listener);
        login.setOnClickListener(l_listener);
    }

    public void addSomeTestSubject() {
        Student s1 = new Student();
        AccountData s_a_1 = new AccountData("student1", "123456");
        s1.setAccountData(s_a_1);
        s1.setStudent_code("20201212");
        s1.setAge(20);
        s1.setName("TestSubject01");
        my_db.addStudent(s1);

        Teacher t1 = new Teacher();
        AccountData t_a_1 = new AccountData("teacher1", "123456");
        t1.setAccountData(t_a_1);
        t1.setAge(30);
        t1.setTeacher_code("2002155");
        t1.setName("Teacher 1");
        my_db.addTeacher(t1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find_view();
        setListener();
        addSomeTestSubject();
    }
}