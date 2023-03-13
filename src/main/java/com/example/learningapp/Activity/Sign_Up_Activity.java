package com.example.learningapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.CustomViews.HideHintEditText;
import com.example.learningapp.CustomViews.MultiHintEditText;
import com.example.learningapp.Data.UserDataBase.AccountData;
import com.example.learningapp.Data.UserDataBase.Student;
import com.example.learningapp.Data.UserDataBase.Teacher;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

import java.util.Objects;

public class Sign_Up_Activity extends AppCompatActivity {
    HideHintEditText account,name,password,password_2,age;
    MultiHintEditText code;
    RadioButton teacher,student,male,female;
    Button signup,back;
    TextView hint;

    public int formCheck() {
        String my_account = account.getText().toString();
        String my_name = name.getText().toString();
        String my_password = password.getText().toString();
        String my_password_2 = password_2.getText().toString();
        String my_age = age.getText().toString();
        String my_code = Objects.requireNonNull(code.getText()).toString();
        if (my_account.matches("")) {
            return 0;
        }
        if (my_name.matches("")) {
            return 0;
        }
        if (my_password.matches("")) {
            return 0;
        }
        if (my_password_2.matches("")) {
            return 0;
        }
        if (my_age.matches("")) {
            return 0;
        }
        if (my_code.matches("")) {
            return 0;
        }
        if (!teacher.isChecked() && !student.isChecked()) {
            return 0;
        }
        if (!male.isChecked() && !female.isChecked()) {
            return 0;
        }
        if (!my_password.matches(my_password_2)) {
            return 1;
        }
        return 2;
    }

    public void LogIn() {
        Intent log_s = new Intent(this, Student_Activity.class);
        Intent log_t = new Intent(this, Teacher_Activity.class);
        if (teacher.isChecked()) {
            log_t.putExtra("teacher_index", my_Data.user_data.getTeacher_last_index());
            startActivity(log_t);
        }
        if (student.isChecked()) {
            log_s.putExtra("student_index", my_Data.user_data.getStudent_last_index());
            startActivity(log_s);
        }
    }

    private int sign_up() {
        if (formCheck() == 0) {
            hint.setText("请完成所有表格");
            hint.setVisibility(View.VISIBLE);
            return 0;
        }
        if (formCheck() == 1) {
            hint.setText("两次输入的密码不匹配");
            hint.setVisibility(View.VISIBLE);
            return 0;
        }
        if (formCheck() == 2) {
            if (my_Data.user_data.match_account(account.getText().toString())) {
                return 1;
            }
            if (!my_Data.user_data.check_T_code_valid(code.getText().toString()) && teacher.isChecked()){
                return 3;
            }
            if (!my_Data.user_data.check_S_code_valid(code.getText().toString())&& student.isChecked()){
                return 3;
            }
        }
        return 2;
    }

    private void create_account() {
        Teacher new_t;
        Student new_s;
        String my_account = account.getText().toString();
        String my_name = name.getText().toString();
        String my_password = password.getText().toString();
        String my_age = age.getText().toString();
        String my_code = Objects.requireNonNull(code.getText()).toString();
        if (teacher.isChecked()) {
            new_t = new Teacher();
            new_t.setGender(male.isChecked());
            new_t.setName(my_name);
            new_t.setAccountData(new AccountData(my_account, my_password));
            new_t.setAge(Integer.parseInt(my_age));
            new_t.setTeacher_code(my_code);
            my_Data.user_data.addTeacher(new_t);
        }
        if (student.isChecked()) {
            new_s = new Student();
            new_s.setGender(male.isChecked());
            new_s.setName(my_name);
            new_s.setAccountData(new AccountData(my_account, my_password));
            new_s.setAge(Integer.parseInt(my_age));
            new_s.setStudent_code(my_code);
            my_Data.user_data.addStudent(new_s);
        }
    }

    private void LogBack() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    View.OnClickListener backListener = v -> LogBack();
    View.OnClickListener signListener = v -> {
        int status = sign_up();
        if (status == 1) {
            hint.setText("用户名已存在");
            hint.setVisibility(View.VISIBLE);
            return;
        }
        if (status == 2) {
            Toast.makeText(this, "用户创建成功", Toast.LENGTH_LONG).show();
            create_account();
            LogIn();
        }
        if (status == 3){
            if(teacher.isChecked()){
                Toast.makeText(this, "教师编号与其他编号冲突", Toast.LENGTH_LONG).show();
                return;
            }
            if(student.isChecked()){
                Toast.makeText(this, "学号与其他人存在冲突", Toast.LENGTH_LONG).show();
            }
        }
    };
    View.OnClickListener TListener = v -> {
        code.setUseOtherHint(true);
        code.set_my_hint(1);
        code.refresh_hint();
    };
    View.OnClickListener SListener = v -> {
        code.setUseOtherHint(true);
        code.set_my_hint(2);
        code.refresh_hint();
    };

    public void setListener() {
        back.setOnClickListener(backListener);
        signup.setOnClickListener(signListener);
        teacher.setOnClickListener(TListener);
        student.setOnClickListener(SListener);
    }

    public void findViews() {
        account = findViewById(R.id.signup_account);
        name = findViewById(R.id.signup_name);
        password = findViewById(R.id.signup_password);
        password_2 = findViewById(R.id.signup_password2);
        teacher = findViewById(R.id.signup_T);
        student = findViewById(R.id.signup_s);
        male = findViewById(R.id.signup_g_m);
        female = findViewById(R.id.signup_g_f);
        age = findViewById(R.id.signup_age);
        code = findViewById(R.id.signup_code);
        signup = findViewById(R.id.signup_button);
        back = findViewById(R.id.signup_back);
        hint = findViewById(R.id.signup_hint);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViews();
        setListener();
        //setup dataBase
        hint.setVisibility(View.INVISIBLE);
        code.add_hint(1, "请填写教师编号");
        code.add_hint(2, "请输入学号");
    }
}