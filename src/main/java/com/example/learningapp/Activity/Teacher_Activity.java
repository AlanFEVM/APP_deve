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

public class Teacher_Activity extends AppCompatActivity {
    int teacher_index;
    Button edit_info, class_room, logout;
    Intent lastIn;
    TextView name;
    boolean have_class_room;

    private void goCreateClassRoom() {
        Intent myIntent = new Intent(this, CreateClassRoom_Activity.class);
        myIntent.putExtra("teacher_index", teacher_index);
        startActivity(myIntent);
    }

    private void check_classroom() {
        if (my_Data.user_data.getTeacher(teacher_index).getClassroom_index() == -1) {
            class_room.setText("创建教室");
            have_class_room = false;
        } else {
            class_room.setText("进入教室");
            have_class_room = true;
        }
    }

    public void goManageClassRoom() {
        Intent to_class_room = new Intent(this, Teacher_ClassRoom_Management_Activity.class);
        to_class_room.putExtra("teacher_index", teacher_index);
        startActivity(to_class_room);
    }

    View.OnClickListener E_Listener = v -> {
        Intent to_info = new Intent(this, EditInfoActivity.class);
        to_info.putExtra("type", 1);
        to_info.putExtra("teacher_index", teacher_index);
        startActivity(to_info);
    };

    View.OnClickListener Class_Room_L = v -> {
        if (!have_class_room) {
            goCreateClassRoom();
        } else {
            goManageClassRoom();
        }
    };

    View.OnClickListener logoutL = v -> {
        gologout();
    };

    private void gologout(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @SuppressLint("SetTextI18n")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        if (teacher_index == -1) {
            Toast.makeText(this, "登录错误，请返回重试", Toast.LENGTH_SHORT).show();
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
        }

        edit_info = findViewById(R.id.teacher_EditInfo_button);
        name = findViewById(R.id.teacher_title);
        logout = findViewById(R.id.teacher_logout);
        class_room = findViewById(R.id.teacher_class_room);
        edit_info.setOnClickListener(E_Listener);
        class_room.setOnClickListener(Class_Room_L);
        logout.setOnClickListener(logoutL);

        lastIn = getIntent();
        teacher_index = lastIn.getIntExtra("teacher_index", -1);
        name.setText("Hello " + my_Data.user_data.getTeacher(teacher_index).getName());
        check_classroom();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestart() {
        super.onRestart();
        name.setText("Hello " + my_Data.user_data.getTeacher(teacher_index).getName());
        check_classroom();
    }
}
