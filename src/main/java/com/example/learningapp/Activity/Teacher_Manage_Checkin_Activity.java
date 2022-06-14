package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learningapp.Data.ClassRoom.CheckIn;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;
import com.example.learningapp.recyclerViewAdapter.Teacher_manage_checkins_RV_Adapter;

import java.util.ArrayList;

public class Teacher_Manage_Checkin_Activity extends AppCompatActivity implements Teacher_manage_checkins_RV_Adapter.buttonListener {
    Intent lastI;
    int teacher_idnex;
    int classroom_index;
    RecyclerView checkin_Rv;
    Teacher_manage_checkins_RV_Adapter adapter;
    ArrayList<CheckIn> allCheckin;
    Button back;
    private void back_f(){
        Intent intent = new Intent(this,Teacher_ClassRoom_Management_Activity.class);
        intent.putExtra("teacher_index",teacher_idnex);
        startActivity(intent);
    }

    @Override
    public void lockup(int position) {
        allCheckin.get(position).lockCheckIn();
        adapter = new Teacher_manage_checkins_RV_Adapter(this,allCheckin,this);
        checkin_Rv.setAdapter(adapter);
    }

    @Override
    public void viewStudent(int position) {
        Intent intent = new Intent(this, Teacher_view_unchecked_students_activity.class);
        intent.putExtra("teacher_index",teacher_idnex);
        intent.putExtra("classroom_index",classroom_index);
        intent.putExtra("check_index",allCheckin.get(position).getCheckIn_index());
        startActivity(intent);
    }

    View.OnClickListener backL = v -> {
        back_f();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_manage_checkin);
        lastI = getIntent();
        teacher_idnex = lastI.getIntExtra("teacher_index",-1);
        classroom_index = my_Data.user_data.getTeacher(teacher_idnex).getClassroom_index();
        allCheckin = my_Data.my_class_room.get(classroom_index).getAllCheckin();

        checkin_Rv = findViewById(R.id.teacher_checkIn_manage_RV);
        back = findViewById(R.id.teacher_checkIn_manage_card_back);

        adapter = new Teacher_manage_checkins_RV_Adapter(this,allCheckin,this);
        checkin_Rv.setAdapter(adapter);
        checkin_Rv.setLayoutManager(new LinearLayoutManager(this));
        back.setOnClickListener(backL);
    }
}