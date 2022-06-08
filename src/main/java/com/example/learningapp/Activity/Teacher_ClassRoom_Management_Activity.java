package com.example.learningapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

public class Teacher_ClassRoom_Management_Activity extends AppCompatActivity {
    int teacher_index;
    Button view_student_btn, add_student_btn, add_sign_btn, random_pickup_btn, homework_btn, questions_btn, get_back_btn;
    TextView class_name, class_code, class_student_num;
    Intent lastI;

    public void log_back() {
        Intent back = new Intent(this, Teacher_Activity.class);
        back.putExtra("teacher_index", teacher_index);
        startActivity(back);
    }

    View.OnClickListener Back_listener = v -> log_back();

    public void setListener() {
        get_back_btn.setOnClickListener(Back_listener);
    }

    private void findViews() {
        get_back_btn = findViewById(R.id.class_room_manage_back);
        questions_btn = findViewById(R.id.class_room_manage_question);
        homework_btn = findViewById(R.id.class_room_manage_homework);
        random_pickup_btn = findViewById(R.id.class_room_manage_random_pickup);
        add_sign_btn = findViewById(R.id.class_room_manage_publish_sign);
        add_student_btn = findViewById(R.id.class_room_manage_add_student_btn);
        view_student_btn = findViewById(R.id.class_room_manage_view_student_btn);
        class_name = findViewById(R.id.class_room_manage_class_name);
        class_code = findViewById(R.id.class_room_manage_class_code);
        class_student_num = findViewById(R.id.class_room_manage_class_student_num);
    }
    View.OnClickListener addStudentListener = v -> to_add_student();
    private void to_add_student(){
        Intent intent = new Intent(this,AddStudentActivity.class);
        intent.putExtra("teacher_index",teacher_index);
        startActivity(intent);
    }
    public void goViewStudent(){
        Intent intent = new Intent(this,ViewStudent_Activity.class);
        intent.putExtra("teacher_index",teacher_index);
        startActivity(intent);
    }
    View.OnClickListener viewStudentBtnListener = v -> goViewStudent();
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_room_management);
        lastI = getIntent();
        teacher_index = lastI.getIntExtra("teacher_index", -1);
        findViews();
        setListener();

        class_name.setText("课程名称：" + my_Data.find_ClassRoomByTeacherClassRoomIndex(teacher_index).getCourse_name());
        class_code.setText("课程编号：" + my_Data.find_ClassRoomByTeacherClassRoomIndex(teacher_index).getClass_code());
        class_student_num.setText("班级人数：" + my_Data.find_ClassRoomByTeacherClassRoomIndex(teacher_index).get_student_num());
        add_student_btn.setOnClickListener(addStudentListener);
        view_student_btn.setOnClickListener(viewStudentBtnListener);
    }
}