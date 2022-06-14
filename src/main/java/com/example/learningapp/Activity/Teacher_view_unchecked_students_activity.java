package com.example.learningapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.UserDataBase.Student;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;
import com.example.learningapp.recyclerViewAdapter.UncheckedStudent_RV_Adapter;

import java.util.ArrayList;

public class Teacher_view_unchecked_students_activity extends AppCompatActivity {
    ArrayList<Student> studentList;
    ArrayList<Integer> studentList_index;
    Intent lastI;
    int teacher_index;
    int classroom_index;
    int checkin_index;
    UncheckedStudent_RV_Adapter adapter;
    Button back;
    RecyclerView student_rv;
    private void goback(){
        Intent intent = new Intent(this,Teacher_Manage_Checkin_Activity.class);
        intent.putExtra("teacher_index",teacher_index);
        intent.putExtra("classroom_idnex",classroom_index);
        startActivity(intent);
    }
    View.OnClickListener backL= v -> {
        goback();
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teacher_view_unchecked_students);

        studentList = new ArrayList<>();

        student_rv = findViewById(R.id.unchecked_student_RV);
        back = findViewById(R.id.unchecked_student_back);
        back.setOnClickListener(backL);

        lastI = getIntent();
        checkin_index = lastI.getIntExtra("check_index", -1);
        teacher_index = lastI.getIntExtra("teacher_index", -1);

        classroom_index = my_Data.user_data.getTeacher(teacher_index).getClassroom_index();
        studentList_index = my_Data.my_class_room.get(classroom_index).getCheckIn(checkin_index)./*获取签到*/
                getUncheckedStudentIndex(my_Data.my_class_room.get(classroom_index).getMy_student());
        for(int i = 0; i < studentList_index.size();i++){
            studentList.add(my_Data.user_data.getStudent(studentList_index.get(i)));
        }

        adapter = new UncheckedStudent_RV_Adapter(this,studentList);
        student_rv.setAdapter(adapter);
        student_rv.setLayoutManager(new LinearLayoutManager(this));
    }
}