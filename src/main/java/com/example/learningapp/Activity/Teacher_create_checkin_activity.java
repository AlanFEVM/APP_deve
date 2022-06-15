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

public class Teacher_create_checkin_activity extends AppCompatActivity {
    int teacher_index;
    EditText password_ET, checkIn_name;
    Button create, back;
    Intent lastI;
    String password;
    int class_room_index;
    boolean has_password;
    View.OnClickListener create_Listener = v -> {
        if(checkIn_name.getText().toString().matches("")){
            Toast.makeText(this,"请输入签到名称",Toast.LENGTH_LONG).show();
        }else{
            if (my_Data.my_class_room.get(class_room_index).get_student_num() > 0) {
                checkInPwdCheck();
                createCheckIn();
                sendMessages();
                Toast.makeText(this, "成功发出签到，请返回查看", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "班级里面还没有学生，无法发出签到", Toast.LENGTH_LONG).show();
            }
        }
    };
    View.OnClickListener back_Listener = v -> goBack();

    public void createCheckIn() {
        my_Data.my_class_room.get(class_room_index).create_checkIn(password, has_password,checkIn_name.getText().toString());
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
            int index = (my_Data.my_class_room.get(class_room_index).getCheckInSize() - 1);
            my_Data.user_data.getStudent(student_index).create_Message(class_room_index, "签到", "你有一个签到需要完成,签到名称：" + checkIn_name.getText().toString());
        }
    }

    private void findViews() {
        password_ET = findViewById(R.id.teacher_create_sign_password);
        create = findViewById(R.id.teacher_create_sign_btn);
        back = findViewById(R.id.teacher_create_sign_back);
        checkIn_name = findViewById(R.id.teacher_create_sign_name);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_checkin);
        findViews();
        lastI = getIntent();
        teacher_index = lastI.getIntExtra("teacher_index", -1);
        class_room_index = my_Data.user_data.getTeacher(teacher_index).getClassroom_index();
        create.setOnClickListener(create_Listener);
        back.setOnClickListener(back_Listener);
    }
}
