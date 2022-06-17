package com.example.learningapp.Activity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.CustomViews.HideHintEditText;
import com.example.learningapp.Data.UserDataBase.Student;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

public class EditInfoActivity extends AppCompatActivity {
    // -----------------------------------variables------------------------------------------------
    Button edit_info_button, change_password_button, pwd_confirm_button,back;
    LinearLayout edit_info_layout, change_password_layout;
    TextView title, info_account, info_name, info_gender, info_age, info_code, info_hint, info_confirm_button, pwd_hint;
    Button info_cancel, password_cancel;
    HideHintEditText name_change, age_change, code_change, old_pwd, new_pwd, new_pwd2;
    RadioButton gender_male, gender_female;
    //当前学生或教师索引
    int personIndex = -1;
    //类别 1 表示 教师， 0 表示学生
    int Person_type = -1;
    //---------------------------------------------------------------------------------------------
    //------------------------------- functions ---------------------------------------------------
    //表格检测
    private int info_check() {
        String name = name_change.getText().toString();
        String age = age_change.getText().toString();
        String code = code_change.getText().toString();
        if (name.matches("") || age.matches("") || code.matches("")) {
            return 0;
        }
        if(Person_type == 1){
            if(my_Data.user_data.check_T_code_valid(code)){
                return 2;
            }
            setNewInfo(name, age, code);
            return 1;
        }
        if(Person_type == 2){
            if(my_Data.user_data.check_S_code_valid(code)){
                return 2;
            }
            setNewInfo(name, age, code);
            return 1;
        }
        return 3;
    }
    //设置基础信息
    private void setNewInfo(String name, String age, String code) {
        if (Person_type == 0) {
            my_Data.user_data.getStudent(personIndex).setName(name);
            my_Data.user_data.getStudent(personIndex).setAge(Integer.parseInt(age));
            my_Data.user_data.getStudent(personIndex).setStudent_code(code);
            my_Data.user_data.getStudent(personIndex).setGender(gender_male.isChecked());
        }
        if (Person_type == 1) {
            my_Data.user_data.getTeacher(personIndex).setName(name);
            my_Data.user_data.getTeacher(personIndex).setAge(Integer.parseInt(age));
            my_Data.user_data.getTeacher(personIndex).setTeacher_code(code);
            my_Data.user_data.getTeacher(personIndex).setGender(gender_male.isChecked());
        }
    }
    //密码修改检测
    private int pwdCheck() {
        if (Person_type == 0) {
            if (old_pwd.getText().toString().matches("") || new_pwd.getText().toString().matches("") || new_pwd2.getText().toString().matches("")) {
                return -1;
                //输入框不全
            }
            if (!old_pwd.getText().toString().matches(my_Data.user_data.getStudent(personIndex).getAccountData().getPassword())) {
                return 0;
                //旧密码错误
            } else {
                if (new_pwd.getText().toString().matches(new_pwd2.getText().toString())) {
                    return 1;
                    //新密码匹配成功
                } else {
                    return -2;
                    //新密码匹配不成功
                }
            }
        }
        if (Person_type == 1) {
            if (old_pwd.getText().toString().matches("") || new_pwd.getText().toString().matches("") || new_pwd2.getText().toString().matches("")) {
                return -1;
                //输入框不全
            }
            if (!old_pwd.getText().toString().matches(my_Data.user_data.getTeacher(personIndex).getAccountData().getPassword())) {
                return 0;
                //旧密码错误
            } else {
                if (new_pwd.getText().toString().matches(new_pwd2.getText().toString())) {
                    return 1;
                    //新密码匹配成功
                } else {
                    return -2;
                    //新密码匹配不成功
                }
            }
        }
        return -3;
    }
    //新密码设置
    private void setNewPwd() {
        if (Person_type == 0) {
            my_Data.user_data.getStudent(personIndex).getAccountData().reset_password(new_pwd.getText().toString());
            Toast.makeText(this, "新密码设置成功", Toast.LENGTH_LONG).show();
            pwd_hint.setText("");
            change_password_layout.setVisibility(View.GONE);
        }
        if (Person_type == 1) {
            my_Data.user_data.getTeacher(personIndex).getAccountData().reset_password(new_pwd.getText().toString());
            Toast.makeText(this, "新密码设置成功", Toast.LENGTH_LONG).show();
            pwd_hint.setText("");
            change_password_layout.setVisibility(View.GONE);
        }
        old_pwd.setText("");
        new_pwd.setText("");
        new_pwd2.setText("");
    }
    private void goStudent(){
        Intent intent = new Intent(this, Student_Activity.class);
        intent.putExtra("student_index",personIndex);
        startActivity(intent);
    }
    private void goTeacher(){
        Intent intent = new Intent(this,Teacher_Activity.class);
        intent.putExtra("teacher_index",personIndex);
        startActivity(intent);
    }
    //---------------------------------------------------------------------------------------------
    //----------------------Listener---------------------------------------------------------------
    //设置监听器
    View.OnClickListener change_pwd_Listener = v -> {
        edit_info_layout.setVisibility(View.GONE);
        change_password_layout.setVisibility(View.VISIBLE);
    };
    View.OnClickListener I_C_L = v -> {
        edit_info_layout.setVisibility(View.GONE);
        info_hint.setHint("");
    };
    View.OnClickListener P_C_L = v -> {
        change_password_layout.setVisibility(View.GONE);
        old_pwd.setText("");
        new_pwd.setText("");
        new_pwd2.setText("");
        pwd_hint.setHint("");
    };
    View.OnClickListener info_confirm_listener = v -> {
        int i = info_check();
        if (i == 0) {
            info_hint.setHint("请完成所需信息");
        }
        if (i == 1) {
            set_info();
            Toast.makeText(this, "成功修改信息", Toast.LENGTH_LONG).show();
            info_hint.setHint("");
            edit_info_layout.setVisibility(View.GONE);
        }
        if(i == 2){
            if(Person_type == 1){
                Toast.makeText(this, "教师编号冲突", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "学号冲突", Toast.LENGTH_LONG).show();
            }
        }
    };
    View.OnClickListener edit_info_Listener = v -> {
        edit_info_layout.setVisibility(View.VISIBLE);
        change_password_layout.setVisibility(View.GONE);
        if (Person_type == 0) {
            code_change.setHint("学号");
        }
        if (Person_type == 1) {
            code_change.setHint("输入教师编号");
        }
        autoFillInfo();
    };
    View.OnClickListener pwd_confirm_listener = v -> {
        int status = pwdCheck();
        if (status == -1) {
            pwd_hint.setText("请补全输入框");
        }
        if (status == 0) {
            pwd_hint.setText("旧密码错误");
        }
        if (status == -2) {
            pwd_hint.setText("输入的新密码不匹配");
        }
        if (status == 1) {
            setNewPwd();
        }
    };
    View.OnClickListener back_L = v -> {
        if(Person_type == 0){
            goStudent();
        }
        if(Person_type == 1){
            goTeacher();
        }
    };
    //---------------------------------------------------------------------------------------------
    //---------------------------Basic Setup-------------------------------------------------------
    //Activity 基础设置
    private void setListener() {
        edit_info_button.setOnClickListener(edit_info_Listener);
        change_password_button.setOnClickListener(change_pwd_Listener);
        info_cancel.setOnClickListener(I_C_L);
        password_cancel.setOnClickListener(P_C_L);
        info_confirm_button.setOnClickListener(info_confirm_listener);
        pwd_confirm_button.setOnClickListener(pwd_confirm_listener);
        back.setOnClickListener(back_L);
    }
    private void get_Data() {
        Intent lastIntent = getIntent();
        Person_type = lastIntent.getIntExtra("type", -1);
        if (Person_type == 0) {
            personIndex = lastIntent.getIntExtra("student_index", -1);
        }
        if (Person_type == 1) {
            personIndex = lastIntent.getIntExtra("teacher_index", -1);
        }
    }
    private void autoFillInfo() {
        if (Person_type == 0) {
            name_change.setText(my_Data.user_data.getStudent(personIndex).getName());
            age_change.setText(my_Data.user_data.getStudent(personIndex).getAge());
            code_change.setText(my_Data.user_data.getStudent(personIndex).getStudent_code());
            boolean gender = my_Data.user_data.getStudent(personIndex).getGenderBoolean();
            if (gender) {
                gender_male.setChecked(true);
            } else {
                gender_female.setChecked(true);
            }
        }
        if (Person_type == 1) {
            name_change.setText(my_Data.user_data.getTeacher(personIndex).getName());
            age_change.setText(my_Data.user_data.getTeacher(personIndex).getAge());
            code_change.setText(my_Data.user_data.getTeacher(personIndex).getTeacher_code());
            boolean gender = my_Data.user_data.getTeacher(personIndex).getGenderBoolean();
            if (gender) {
                gender_male.setChecked(true);
            } else {
                gender_female.setChecked(true);
            }
        }
    }
    private void findViews() {
        edit_info_button = findViewById(R.id.info_EditInfo_button);
        change_password_button = findViewById(R.id.info_ChangePwd_button);
        edit_info_layout = findViewById(R.id.info_EditInfo_layout);
        change_password_layout = findViewById(R.id.info_ChangePwd_layout);
        title = findViewById(R.id.info_title);
        info_account = findViewById(R.id.info_account);
        info_name = findViewById(R.id.info_name);
        info_gender = findViewById(R.id.info_gender);
        info_age = findViewById(R.id.info_age);
        info_code = findViewById(R.id.info_code);
        info_cancel = findViewById(R.id.info_changeInfoCancel_button);
        password_cancel = findViewById(R.id.info_password_cancel);
        name_change = findViewById(R.id.info_changeName);
        age_change = findViewById(R.id.info_changeAge);
        code_change = findViewById(R.id.info_changeCode);
        gender_male = findViewById(R.id.info_gender_male);
        gender_female = findViewById(R.id.info_gender_female);
        info_hint = findViewById(R.id.info_ChangeInfo_hint);
        info_confirm_button = findViewById(R.id.info_confirm_info);
        old_pwd = findViewById(R.id.info_changePwd_old);
        new_pwd = findViewById(R.id.info_changePwd_new);
        new_pwd2 = findViewById(R.id.info_changePwd_new2);
        pwd_confirm_button = findViewById(R.id.info_confirm_password);
        pwd_hint = findViewById(R.id.info_ChangePassword_hint);
        back = findViewById(R.id.info_EditInfo_back);
    }
    @SuppressLint("SetTextI18n")
    private void set_info() {
        //student setup
        if (Person_type == 0) {
            title.setText("学生信息管理");
            info_account.setText("用户名: " + my_Data.user_data.getStudent(personIndex).getAccountData().getAccount_name());
            info_name.setText("姓名: " + my_Data.user_data.getStudent(personIndex).getName());
            info_age.setText("年龄: " + my_Data.user_data.getStudent(personIndex).getAge());
            info_gender.setText("性别: " + my_Data.user_data.getStudent(personIndex).getGender());
            info_code.setText("学号: " + my_Data.user_data.getStudent(personIndex).getStudent_code());
        }
        //teacher setup
        if (Person_type == 1) {
            title.setText("教师信息管理");
            info_account.setText("用户名: " + my_Data.user_data.getTeacher(personIndex).getAccountData().getAccount_name());
            info_name.setText("姓名: " + my_Data.user_data.getTeacher(personIndex).getName());
            info_age.setText("年龄: " + my_Data.user_data.getTeacher(personIndex).getAge());
            info_gender.setText("性别: " + my_Data.user_data.getTeacher(personIndex).getGender());
            info_code.setText("教师编号: " + my_Data.user_data.getTeacher(personIndex).getTeacher_code());
        }
    }
    //---------------------------------------------------------------------------------------------
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