package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapp.recyclerViewAdapter.ClassRoomSearch_RV_Adapter;
import com.example.learningapp.R;
import com.example.learningapp.Data.my_Data;

import java.util.ArrayList;

public class Student_join_activity extends AppCompatActivity implements ClassRoomSearch_RV_Adapter.class_room_button_click{
    ArrayList<Integer> Class_Room_Index_for_RV;
    Intent LastI;
    RecyclerView RV;
    ClassRoomSearch_RV_Adapter adapter;
    TextView name_tv, code_tv;
    EditText code;
    Button search,confirm,back;
    int student_index;
    int class_room_to_join_index;
    View.OnClickListener search_Listener = v -> findClassRoom();
    View.OnClickListener confirm_listener = v -> joinClass();
    View.OnClickListener back_L = v -> goBack();

    @Override
    public void onClassBtnClick(int position) {
        class_room_to_join_index = Class_Room_Index_for_RV.get(position);
        name_tv.setText(my_Data.my_class_room.get(class_room_to_join_index).getCourse_name());
        code_tv.setText(my_Data.my_class_room.get(class_room_to_join_index).getClass_code());
    }
    private void goBack(){
        Intent intent = new Intent(this,Student_Activity.class);
        intent.putExtra("student_index",student_index);
        startActivity(intent);
    }

    private void findClassRoom() {
        class_room_to_join_index = my_Data.findClassRoomByCode(code.getText().toString());
        if (class_room_to_join_index == -1) {
            Toast.makeText(this, "搜索失败，教室不存在！", Toast.LENGTH_LONG).show();
            name_tv.setText("");
            code_tv.setText("");
        } else {
            Toast.makeText(this, "搜索结果如下，请确认加入", Toast.LENGTH_LONG).show();
            name_tv.setText(my_Data.my_class_room.get(class_room_to_join_index).getCourse_name());
            code_tv.setText(my_Data.my_class_room.get(class_room_to_join_index).getClass_code());
        }
    }

    private void joinClass() {
        if(class_room_to_join_index != -1){
            boolean tag = my_Data.user_data.getStudent(student_index).add_classRoom(class_room_to_join_index);
            if(tag){
                Toast.makeText(this,"加入成功",Toast.LENGTH_LONG).show();
                my_Data.my_class_room.get(class_room_to_join_index).addStudent_By_student_index(student_index);
                my_Data.user_data.getStudent(student_index).add_Message_queue(class_room_to_join_index);
                setRv();
            }
        }else{
            Toast.makeText(this,"加入失败，没有选定教室",Toast.LENGTH_LONG).show();
        }
    }

    private void findViews() {
        RV = findViewById(R.id.student_join_RV);
        code = findViewById(R.id.student_join_code_search);
        name_tv = findViewById(R.id.student_join_classname);
        code_tv = findViewById(R.id.student_join_classCode);
        search = findViewById(R.id.student_join_search_btn);
        confirm = findViewById(R.id.student_join_confirm_btn);
        back = findViewById(R.id.student_join_back_btn);
    }
    private void setRv(){
        Class_Room_Index_for_RV = my_Data.findClassRoom_index_that_Student_is_not_in(my_Data.user_data.getStudent(student_index).getClass_room_array());
        adapter = new ClassRoomSearch_RV_Adapter(this, Class_Room_Index_for_RV,this);
        RV.setAdapter(adapter);
        RV.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_join);
        LastI = getIntent();
        student_index = LastI.getIntExtra("student_index", -1);
        findViews();
        setRv();
        search.setOnClickListener(search_Listener);
        confirm.setOnClickListener(confirm_listener);
        back.setOnClickListener(back_L);
    }
}