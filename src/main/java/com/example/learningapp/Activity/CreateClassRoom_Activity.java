package com.example.learningapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.CustomViews.HideHintEditText;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

public class CreateClassRoom_Activity extends AppCompatActivity {
    Intent lastI;
    Button cancel, create_classroom;
    HideHintEditText ET_name, ET_code;
    TextView hint;
    int teacher_index;

    private void goBack() {
        Intent back = new Intent(this, Teacher_Activity.class);
        back.putExtra("teacher_index", teacher_index);
        startActivity(back);
    }

    private final View.OnClickListener CancelButtonListener = v -> goBack();

    View.OnClickListener create_listener = v -> {
        if (ET_name.getText().toString().matches("") || ET_code.getText().toString().matches("")) {
            hint.setText("请完成表格");
            hint.setVisibility(View.VISIBLE);
            return;
        } else {
            if (my_Data.user_data.getTeacher(teacher_index).getClassroom_index() == -1) {
                if (my_Data.checkClassRoomCodeValid(ET_code.getText().toString())) {
                    Creat_class_room();
                    Toast.makeText(this, "成功创建教室，请返回查看", Toast.LENGTH_LONG).show();
                } else {
                    hint.setText("教室编号已存在，请尝试换一个");
                    hint.setVisibility(View.VISIBLE);
                }
            } else {
                hint.setText("您已经创建好教室了，请查看");
                hint.setVisibility(View.VISIBLE);
            }
        }
    };

    public void Creat_class_room() {
        String C_name = ET_name.getText().toString();
        String C_code = ET_code.getText().toString();
        my_Data.create_class_room(teacher_index, C_name, C_code);
        my_Data.user_data.getTeacher(teacher_index).setClassroom_index(my_Data.getLastClassRoomIndex());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class_room);

        lastI = getIntent();
        teacher_index = lastI.getIntExtra("teacher_index", -1);

        ET_name = findViewById(R.id.create_classroom_className);
        ET_code = findViewById(R.id.create_classroom_classCode);
        cancel = findViewById(R.id.create_classroom_button_cancel);
        create_classroom = findViewById(R.id.create_classroom_button_create);
        hint = findViewById(R.id.create_classroom_hint);

        cancel.setOnClickListener(CancelButtonListener);
        create_classroom.setOnClickListener(create_listener);
    }
}