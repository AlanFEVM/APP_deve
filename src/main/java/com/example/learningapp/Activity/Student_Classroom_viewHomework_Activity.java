package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

public class Student_Classroom_viewHomework_Activity extends AppCompatActivity {
    int student_index;
    int classroom_index;
    Intent lastI;
    int homework_index;
    String title, content,score,comment,answer;
    TextView title_et, content_et, score_et, comment_et, answer_et;
    Button back;

    private void findViews(){
        title_et = findViewById(R.id.student_viewHomework_title);
        content_et = findViewById(R.id.student_viewHomework_content);
        score_et = findViewById(R.id.student_viewHomework_score);
        comment_et = findViewById(R.id.student_viewHomework_comment);
        answer_et = findViewById(R.id.student_viewHomework_answer);
        back = findViewById(R.id.student_viewHomework_backbtn);
    }

    private void catchData(){
        title = my_Data.my_class_room.get(classroom_index).getHomeworklist().get(homework_index).getTitle();
        content = my_Data.my_class_room.get(classroom_index).getHomeworklist().get(homework_index).getContent();
        answer = my_Data.my_class_room.get(classroom_index).getHomeworklist().get(homework_index).getHomeworkByStudentIndex(student_index).get_answer();
        score = Integer.toString(my_Data.my_class_room.get(classroom_index).getHomeworklist().get(homework_index).getHomeworkByStudentIndex(student_index).getScore());
        comment = my_Data.my_class_room.get(classroom_index).getHomeworklist().get(homework_index).getHomeworkByStudentIndex(student_index).getComment();
    }

    private void goback(){
        Intent intent = new Intent(this,Student_Homework_Activity.class);
        intent.putExtra("classroom_index",classroom_index);
        intent.putExtra("student_index",student_index);
        startActivity(intent);
    }
    View.OnClickListener back_L = v -> {
        goback();
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_classroom_viewhomework);

        findViews();
        catchData();

        lastI = getIntent();
        student_index = lastI.getIntExtra("student_index",-1);
        classroom_index = lastI.getIntExtra("classroom_index",-1);
        homework_index = lastI.getIntExtra("homework_index",-1);
        back.setOnClickListener(back_L);

        title_et.setText(title);
        content_et.setText(content);
        answer_et.setText(answer);
        score_et.setText(score);
        if(comment.matches("")){
            comment_et.setText("教师没有做出任何评价 (/▽＼)");
        }else{
            comment_et.setText(comment);
        }
    }
}