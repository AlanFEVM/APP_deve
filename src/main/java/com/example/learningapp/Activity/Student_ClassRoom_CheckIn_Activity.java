package com.example.learningapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.ClassRoom.CheckIn;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;
import com.example.learningapp.recyclerViewAdapter.StudentCheckIn_RV_Adapter;

import java.util.ArrayList;

public class Student_ClassRoom_CheckIn_Activity extends AppCompatActivity {
    int student_index;
    int classroom_index;
    Intent lastI;
    Button back;
    RecyclerView checkinRV;
    ArrayList<CheckIn> checkInsto_Rv;
    StudentCheckIn_RV_Adapter adapter;
    private void findViews() {
        back = findViewById(R.id.student_checkIn_back_btn);
        checkinRV = findViewById(R.id.student_checkIn_RV);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_room_check_in);
        findViews();
        lastI = getIntent();
        student_index = lastI.getIntExtra("student_index", -1);
        classroom_index = lastI.getIntExtra("classroom_index", -1);

        checkInsto_Rv = my_Data.my_class_room.get(classroom_index).findNotCheckedCheckIns(student_index);
        adapter = new StudentCheckIn_RV_Adapter(this,checkInsto_Rv);
        checkinRV.setAdapter(adapter);
        checkinRV.setLayoutManager(new LinearLayoutManager(this));
    }
}