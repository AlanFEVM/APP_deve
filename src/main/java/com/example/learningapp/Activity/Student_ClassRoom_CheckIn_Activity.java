package com.example.learningapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.ClassRoom.CheckIn;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;
import com.example.learningapp.recyclerViewAdapter.StudentCheckIn_RV_Adapter;

import java.util.ArrayList;

public class Student_ClassRoom_CheckIn_Activity extends AppCompatActivity implements StudentCheckIn_RV_Adapter.signButtonListener {
    int student_index,classroom_index;
    Intent lastI;
    Button back;
    RecyclerView checkinRV;
    ArrayList<CheckIn> checkInsto_Rv;
    StudentCheckIn_RV_Adapter adapter;
    View.OnClickListener backL = v -> {
        goback();
    };
    private void goback(){
        Intent intent = new Intent(this,Student_ClassRoom_Activity.class);
        intent.putExtra("student_index",student_index);
        intent.putExtra("classroom_index",classroom_index);
        startActivity(intent);
    }
    private void findViews() {
        back = findViewById(R.id.student_checkIn_back_btn);
        checkinRV = findViewById(R.id.student_checkIn_RV);
        back.setOnClickListener(backL);
    }

    public void check(int position) {
        checkInsto_Rv.get(position).checkIn_add_student(student_index);
    }

    public void gotopassword(int checkindex) {
        Intent intent = new Intent(this, Student_checkin_password_activity.class);
        intent.putExtra("student_index", student_index);
        intent.putExtra("classroom_index", classroom_index);
        intent.putExtra("checkIndex", checkindex);
        startActivity(intent);
    }

    @Override
    public void checkIn(int position) {
        if(!checkInsto_Rv.get(position).isLocked()){
            if (checkInsto_Rv.get(position).has_password()) {
                gotopassword(checkInsto_Rv.get(position).getCheckIn_index());
            } else {
                check(position);
                Toast.makeText(this,"签到成功",Toast.LENGTH_SHORT).show();
                setRecyclerView();
            }
        } else{
            Toast.makeText(this,"签到已停止，签到失败",Toast.LENGTH_SHORT).show();
        }
    }

    private void setRecyclerView() {
        checkInsto_Rv = my_Data.my_class_room.get(classroom_index).findNotCheckedCheckIns(student_index);
        adapter = new StudentCheckIn_RV_Adapter(this, checkInsto_Rv, this);
        checkinRV.setAdapter(adapter);
        checkinRV.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_room_check_in);
        findViews();
        lastI = getIntent();
        student_index = lastI.getIntExtra("student_index", -1);
        classroom_index = lastI.getIntExtra("classroom_index", -1);
        setRecyclerView();
    }
}