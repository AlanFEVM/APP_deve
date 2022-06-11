package com.example.learningapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

public class Teacher_create_sign extends AppCompatActivity {
    int teacher_index;
    EditText password_ET;
    Button create, back;
    Intent lastI;
    String password;
    int class_room_index;
    boolean has_password;
    View.OnClickListener create_Listener = v -> {
        if (my_Data.my_class_room.get(class_room_index).get_student_num() > 0) {
            checkInPwdCheck();
            createCheckIn();
            sendMessages();
        }
        Toast.makeText(this, "班级里面还没有学生，无法发出签到", Toast.LENGTH_LONG).show();
    };
    View.OnClickListener back_Listener = v -> goBack();

    public void createCheckIn() {
        my_Data.my_class_room.get(class_room_index).create_checkIn(password, has_password);
    }

    private void goBack() {
        Intent intent = new Intent(this, Teacher_ClassRoom_Management_Activity.class);
        intent.putExtra("teacher_index", teacher_index);
        startActivity(intent);
    }

    private void checkInPwdCheck() {
        if (password_ET.getText().toString().matches("")) {
            password = "";
            has_password = false;
        } else {
            has_password = true;
            password = password_ET.getText().toString();
        }
    }

    private void sendMessages() {
        int student_index;
        for (int i = 0; i < my_Data.my_class_room.get(class_room_index).get_student_num(); i++) {
            student_index = my_Data.my_class_room.get(class_room_index).getMy_student().get(i);
            my_Data.user_data.getStudent(student_index).create_Message(class_room_index, "签到", "你有一个签到需要完成");
        }
    }

    private void findViews() {
        password_ET = findViewById(R.id.teacher_create_sign_password);
        create = findViewById(R.id.teacher_create_sign_btn);
        back = findViewById(R.id.teacher_create_sign_back);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_sign);
        findViews();
        lastI = getIntent();
        teacher_index = lastI.getIntExtra("teacher_index", -1);
        class_room_index = my_Data.user_data.getTeacher(teacher_index).getClassroom_index();
        create.setOnClickListener(create_Listener);
        back.setOnClickListener(back_Listener);
    }
}
