package com.example.learningapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.Message_pack.Message;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;
import com.example.learningapp.recyclerViewAdapter.StudentMessage_RV_adapter;

import java.util.ArrayList;

public class Student_ClassRoom_Messages_Activity extends Activity {
    Intent lastI;
    RecyclerView MessageRV;
    ArrayList<Message> messages;
    int classroom_index;
    int student_index;
    Button back;
    StudentMessage_RV_adapter adapter;

    View.OnClickListener b_l = v -> goback();

    public void goback(){
        Intent intent = new Intent(this,Student_ClassRoom_Activity.class);
        intent.putExtra("student_index",student_index);
        intent.putExtra("classroom_index",classroom_index);
        startActivity(intent);
    }

    private void findViews(){
        MessageRV = findViewById(R.id.student_messagecenter_RV);
        back = findViewById(R.id.student_messagecenter_back_btn);
    }

    private void setListener(){
        back.setOnClickListener(b_l);
    }

    private void setRecyclerView(){
        adapter = new StudentMessage_RV_adapter(this,messages);
        MessageRV.setAdapter(adapter);
        MessageRV.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_classroom_message);
        lastI = getIntent();
        student_index = lastI.getIntExtra("student_index",-1);
        classroom_index = lastI.getIntExtra("classroom_index",-1);
        messages = my_Data.user_data.getStudent(student_index).getClassRoomMessage(classroom_index).getMessages();
        findViews();
        setListener();
        setRecyclerView();
    }
}
