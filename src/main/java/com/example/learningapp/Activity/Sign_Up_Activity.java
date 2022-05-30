package com.example.learningapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.R;

public class Sign_Up_Activity extends AppCompatActivity {
    EditText account;
    EditText name;
    EditText password;
    EditText password_2;
    RadioButton teacher;
    RadioButton student;
    RadioButton male;
    RadioButton female;
    EditText age;
    EditText code;
    Button signup;
    Button back;
    TextView hint;
    public int formCheck(){
        return 1;
    }
    public void Logback(){
        Intent back = new Intent(this,MainActivity.class);
        startActivity(back);
    }

    private void sign_up() {
        String my_account = account.getText().toString();
        String my_name = name.getText().toString();
        String my_password = password.getText().toString();

        password_2.getText().toString();
        age.getText().toString();
        code.getText().toString();

    }
    View.OnClickListener backListener = v -> Logback();

    View.OnClickListener signListener = v -> {
        formCheck();
        sign_up();
        Logback();
    };


    public void setListener(){
        back.setOnClickListener(backListener);
        signup.setOnClickListener(signListener);
    }
    public void findViews(){
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
        hint.setVisibility(View.INVISIBLE);
    }
}