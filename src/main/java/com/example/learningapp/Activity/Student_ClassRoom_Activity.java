package com.example.learningapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

public class Student_ClassRoom_Activity extends AppCompatActivity {
    TextView name, code, t_name, messagecount_tv;
    int student_index, classroom_index, message_counts;
    Intent lastI;
    Button message, questions, homework, back;
    CardView message_count_card;
    View.OnClickListener messageListener = v -> {
        goMessage();
    };
    View.OnClickListener questionsListener = v -> {
        goQuestion();
    };
    View.OnClickListener homeworkListener = v -> {
        goHomework();
    };

    private void goMessage() {
        Intent intent = new Intent(this, Student_ClassRoom_Messages_Activity.class);
        intent.putExtra("student_index", student_index);
        intent.putExtra("classroom_index", classroom_index);
        startActivity(intent);
    }

    private void goQuestion() {

    }

    private void goHomework() {

    }
    View.OnClickListener backListener = v -> {
        goback();
    };
    private void goback(){
        Intent intent = new Intent(this, Student_enterClassRoom_Activity.class);
        intent.putExtra("student_index",student_index);
        startActivity(intent);
    }
    private void findViews(){
        name = findViewById(R.id.student_class_room_classname_tv);
        code = findViewById(R.id.student_class_room_classcode_tv);
        t_name = findViewById(R.id.student_class_room_class_teacher_name);
        message = findViewById(R.id.student_class_room_message_center_btn);
        questions = findViewById(R.id.student_class_room_question_btn);
        homework = findViewById(R.id.student_class_room_home_work_tv);
        back = findViewById(R.id.student_class_room_back_btn);
        message_count_card = findViewById(R.id.student_class_room_message_count_card);
    }
    private void setInfo(){
        name.setText(my_Data.my_class_room.get(classroom_index).getCourse_name());
        code.setText(my_Data.my_class_room.get(classroom_index).getClass_code());
        t_name.setText(my_Data.my_class_room.get(classroom_index).getTeacher_name());
    }

    private void setListeners() {
        message.setOnClickListener(messageListener);
        questions.setOnClickListener(questionsListener);
        homework.setOnClickListener(homeworkListener);
        back.setOnClickListener(backListener);
    }

    private void setMessageCount() {
        message_count_card.setVisibility(View.GONE);
        message_counts = my_Data.user_data.getStudent(student_index).getClassRoomMessage(classroom_index).getUnreadMessagesCounts();
        if (message_counts > 0) {
            message_count_card.setVisibility(View.VISIBLE);
        }
        messagecount_tv.setText(message_counts);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_room);
        lastI = getIntent();
        student_index = lastI.getIntExtra("student_index", -1);
        classroom_index = lastI.getIntExtra("classroom_index", -1);
        findViews();
        setInfo();
        setListeners();
        setMessageCount();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setMessageCount();
    }
}