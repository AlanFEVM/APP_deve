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
    Button edit_info,join_classroom,enter_class_room;
    int student_index;
    Intent lastIn;
    TextView name;
    private void set_Listener(){
        edit_info.setOnClickListener(edit_listener);
        join_classroom.setOnClickListener(join_listener);
        enter_class_room.setOnClickListener(enter_Listener);
    }
    private void findViews() {
        edit_info = findViewById(R.id.student_EditInfo_button);
        name = findViewById(R.id.student_title);
        join_classroom = findViewById(R.id.student_join_classroom);
        enter_class_room = findViewById(R.id.student_enter_classroom);
    }
    private void goJoinClassRoom_AC(){
        Intent intent = new Intent(this,Student_join_activity.class);
        intent.putExtra("student_index",student_index);
        startActivity(intent);
    }

    private void goEnterClassRoomActivity() {
        Intent intent = new Intent(this,Student_enterClassRoom_Activity.class);
        intent.putExtra("student_index",student_index);
        startActivity(intent);
    }

    View.OnClickListener enter_Listener = v -> {
        if(my_Data.user_data.getStudent(student_index).getClass_room_array().size()==0){
            Toast.makeText(this,"您还没有加入任何教室",Toast.LENGTH_LONG).show();
        }else{
            goEnterClassRoomActivity();
        }
    };


    View.OnClickListener edit_listener = v -> {
        Intent to_info = new Intent(this, EditInfoActivity.class);
        to_info.putExtra("type", 0);
        to_info.putExtra("student_index", student_index);
        startActivity(to_info);
    };
    View.OnClickListener join_listener = v -> goJoinClassRoom_AC();
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        lastIn = getIntent();
        student_index = lastIn.getIntExtra("student_index", -1);

        findViews();
        set_Listener();
        name.setText("Hello " + my_Data.user_data.getStudent(student_index).getName());
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestart() {
        super.onRestart();
        name.setText("Hello " + my_Data.user_data.getStudent(student_index).getName());
    }
}