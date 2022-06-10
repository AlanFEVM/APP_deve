package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learningapp.recyclerViewAdapter.ClassRoomInfo_RVAdapter;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

import java.util.ArrayList;

public class Student_enterClassRoom_Activity extends AppCompatActivity implements ClassRoomInfo_RVAdapter.my_button_listener {
    int student_index,classroom_index;
    Context mContext;
    ClassRoomInfo_RVAdapter adapter;
    RecyclerView RV;
    ArrayList<Integer> class_room_index_for_RV;
    Intent lastI;
    Button back;


    public void onButtonClick(int position) {
        classroom_index = class_room_index_for_RV.get(position);
        Intent enter = new Intent(this, Student_ClassRoom_Activity.class);
        enter.putExtra("student_index",student_index);
        enter.putExtra("classroom_index",classroom_index);
        startActivity(enter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_enter_class_room);
        lastI = getIntent();
        student_index = lastI.getIntExtra("student_index",-1);

        class_room_index_for_RV = my_Data.user_data.getStudent(student_index).getClass_room_array();

        RV = findViewById(R.id.student_enter_classroom_RV);
        back = findViewById(R.id.student_enter_classroom_back_btn);
        adapter = new ClassRoomInfo_RVAdapter(this,class_room_index_for_RV,this);
        RV.setAdapter(adapter);
        RV.setLayoutManager(new LinearLayoutManager(this));
        back.setOnClickListener(back_Listener);

    }
    private void goBack(){
        Intent intent = new Intent(this,Student_Activity.class);
        intent.putExtra("student_index",student_index);
        startActivity(intent);
    }
    View.OnClickListener back_Listener = v -> {
        goBack();
    };
}