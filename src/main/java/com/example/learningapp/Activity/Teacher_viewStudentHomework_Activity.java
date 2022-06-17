package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapp.CustomViews.HideHintEditText;
import com.example.learningapp.Data.ClassRoom.StudentHomework;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

public class Teacher_viewStudentHomework_Activity extends AppCompatActivity {
    Intent lastI;
    int classroom_index;
    int teacher_index;
    int student_index;
    int homework_index;
    StudentHomework studentHomework;
    Button confirm,back;
    TextView name, code, answer;
    EditText score;
    HideHintEditText comment;
    private void my_getIntent(){
        lastI = getIntent();
        classroom_index = lastI.getIntExtra("classroom_index",-1);
        student_index = lastI.getIntExtra("student_index",-1);
        homework_index = lastI.getIntExtra("homework_index",-1);
        teacher_index = lastI.getIntExtra("teacher_index",-1);
    }
    private void findViews(){
        confirm = findViewById(R.id.teacher_viewHomework_confirm);
        back = findViewById(R.id.teacher_viewHomework_back);
        name = findViewById(R.id.teacher_viewHomework_student_name);
        code = findViewById(R.id.teacher_viewHomework_student_code);
        answer = findViewById(R.id.teacher_viewHomework_answer);
        score = findViewById(R.id.teacher_viewHomework_score);
        comment = findViewById(R.id.teacher_viewHomework_comment);
    }
    private void setHomeworkcomment(){
        studentHomework.set_score_comment(Integer.parseInt(score.getText().toString()),comment.getText().toString());
        Toast.makeText(this,"批改成功",Toast.LENGTH_LONG).show();
    }
    View.OnClickListener confirm_L = v -> {
        if(score.getText().toString().matches("")){
            Toast.makeText(this,"请填写分数",Toast.LENGTH_LONG).show();
        } else {
            setHomeworkcomment();
        }
    };
    private void goback(){
        Intent intent = new Intent(this,Teacher_manageStudentHomework_Activity.class);
        intent.putExtra("classroom_index",classroom_index);
        intent.putExtra("homework_index",homework_index);
        intent.putExtra("teacher_index",teacher_index);
        startActivity(intent);
    }
    View.OnClickListener back_L = v -> {
        goback();
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view_student_homework);
        my_getIntent();
        findViews();
        studentHomework = my_Data.my_class_room.get(classroom_index).getHomeworklist().get(homework_index).getHomeworkByStudentIndex(student_index);
        answer.setText(studentHomework.get_answer());
        code.setText(my_Data.user_data.getStudent(student_index).getStudent_code());
        name.setText(my_Data.user_data.getStudent(student_index).getName());
        confirm.setOnClickListener(confirm_L);
        back.setOnClickListener(back_L);
    }
}