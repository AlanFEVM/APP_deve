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

public class Teacher_ClassRoom_Management_Activity extends AppCompatActivity {
    int teacher_index;
    int classroom_index;
    Button view_student_btn, add_student_btn, add_checkin_btn, random_pickup_btn, homework_btn,manageHomework, get_back_btn, manage_checkin;
    TextView class_name, class_code, class_student_num;
    Intent lastI;


    private void log_back() {
        Intent back = new Intent(this, Teacher_Activity.class);
        back.putExtra("teacher_index", teacher_index);
        startActivity(back);
    }

    private void goManageCheckins(){
        Intent intent = new Intent(this, Teacher_Manage_Checkin_Activity.class);
        intent.putExtra("teacher_index",teacher_index);
        startActivity(intent);
    }
    private void goCreateCheckIn() {
        Intent intent = new Intent(this, Teacher_create_checkin_activity.class);
        intent.putExtra("teacher_index", teacher_index);
        startActivity(intent);
    }
    private void goCreateHomeWork(){
        Intent intent = new Intent(this,Teacher_create_homework_activity.class);
        intent.putExtra("teacher_index",teacher_index);
        intent.putExtra("classroom_index",classroom_index);
        startActivity(intent);

    }
    private void gomanageHomework(){
        Intent intent = new Intent(this,Teacher_manage_homewrok.class);
        intent.putExtra("teacher_index",teacher_index);
        intent.putExtra("classroom_index",classroom_index);
        startActivity(intent);
    }

    private void to_add_student() {
        Intent intent = new Intent(this, AddStudentActivity.class);
        intent.putExtra("teacher_index", teacher_index);
        startActivity(intent);
    }

    private void goViewStudent() {
        Intent intent = new Intent(this, ViewStudent_Activity.class);
        intent.putExtra("teacher_index", teacher_index);
        startActivity(intent);
    }

    private void setListener() {
        get_back_btn.setOnClickListener(Back_listener);
        add_checkin_btn.setOnClickListener(checkin_L);
        manage_checkin.setOnClickListener(manage_checkinL);
        random_pickup_btn.setOnClickListener(random_pickup_L);
        homework_btn.setOnClickListener(homework_L);
        manageHomework.setOnClickListener(manageHomeworkL);
    }

    private void findViews() {
        get_back_btn = findViewById(R.id.class_room_manage_back);
        homework_btn = findViewById(R.id.class_room_manage_create_homework);
        random_pickup_btn = findViewById(R.id.class_room_manage_random_pickup);
        add_checkin_btn = findViewById(R.id.class_room_manage_publish_checkin);
        add_student_btn = findViewById(R.id.class_room_manage_add_student_btn);
        view_student_btn = findViewById(R.id.class_room_manage_view_student_btn);
        class_name = findViewById(R.id.class_room_manage_class_name);
        class_code = findViewById(R.id.class_room_manage_class_code);
        class_student_num = findViewById(R.id.class_room_manage_class_student_num);
        manage_checkin = findViewById(R.id.class_room_manage_managecheckins);
        manageHomework = findViewById(R.id.class_room_manage_homework);
    }

    View.OnClickListener addStudentListener = v -> to_add_student();
    View.OnClickListener homework_L = v -> goCreateHomeWork();
    View.OnClickListener manageHomeworkL = v -> gomanageHomework();
    View.OnClickListener checkin_L = v -> goCreateCheckIn();
    View.OnClickListener Back_listener = v -> log_back();
    View.OnClickListener manage_checkinL = v -> goManageCheckins();
    View.OnClickListener viewStudentBtnListener = v -> goViewStudent();
    View.OnClickListener random_pickup_L = v -> {
        if(my_Data.find_ClassRoomByTeacherIndex(teacher_index).getMy_student().size()!=0){
            int randS = my_Data.find_ClassRoomByTeacherIndex(teacher_index).random_pickup();
            Toast.makeText(this,"抽到的学生为 " + my_Data.user_data.getStudent(randS).getName(),Toast.LENGTH_LONG).show();
            my_Data.user_data.getStudent(randS).
                    create_Message(my_Data.user_data.getTeacher(teacher_index).getClassroom_index(), "随机点名", "你被抽中啦");
        }else{
            Toast.makeText(this,"班级中还没有学生，功能不可用",Toast.LENGTH_LONG).show();
        }
    };



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_room_management);
        lastI = getIntent();
        teacher_index = lastI.getIntExtra("teacher_index", -1);
        classroom_index = my_Data.user_data.getTeacher(teacher_index).getClassroom_index();
        findViews();
        setListener();

        class_name.setText("课程名称：" + my_Data.find_ClassRoomByTeacherIndex(teacher_index).getCourse_name());
        class_code.setText("课程编号：" + my_Data.find_ClassRoomByTeacherIndex(teacher_index).getClass_code());
        class_student_num.setText("班级人数：" + my_Data.find_ClassRoomByTeacherIndex(teacher_index).get_student_num());

        add_student_btn.setOnClickListener(addStudentListener);
        view_student_btn.setOnClickListener(viewStudentBtnListener);
    }
}