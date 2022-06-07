package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learningapp.Activity.recyclerViewAdapter.ViewStudentRV_Adapter;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

import java.util.ArrayList;

public class ViewStudent_Activity extends AppCompatActivity {
    Intent lastI;
    int teacher_index;
    Button back_bt;
    ArrayList<Integer> student_arr;
    RecyclerView my_Recycer_View;
    public void goback (){
        Intent intent = new Intent(this,Teacher_ClassRoom_Management_Activity.class);
        intent.putExtra("teacher_index",teacher_index);
        startActivity(intent);
    }
    View.OnClickListener back_L = v -> {
        goback();
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        lastI = getIntent();
        teacher_index = lastI.getIntExtra("teacher_index",-1);

        my_Recycer_View = findViewById(R.id.class_room_student_db);
        back_bt = findViewById(R.id.view_Student_back);

        int classroom_index = my_Data.user_data.getTeacher(teacher_index).getClassroom_index();
        student_arr = my_Data.my_class_room.get(classroom_index).getMy_student();

        ViewStudentRV_Adapter adapter = new ViewStudentRV_Adapter(this,student_arr);
        my_Recycer_View.setAdapter(adapter);
        my_Recycer_View.setLayoutManager(new LinearLayoutManager(this));

        back_bt.setOnClickListener(back_L);

    }
}