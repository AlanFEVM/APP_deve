package com.example.learningapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.CustomViews.HideHintEditText;
import com.example.learningapp.R;

public class CreateClassRoom_Activity extends AppCompatActivity {
    Intent lastI;
    Button cancel, create_classroom;
    HideHintEditText ET_name, ET_code;
    int teacher_index;
    private void goBack() {
        Intent back = new Intent(this, Teacher_Activity.class);
        back.putExtra("teacher_index",teacher_index);
        startActivity(back);
    }

    private final View.OnClickListener CancelButtonListener = v -> {
        goBack();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lastI = getIntent();
        teacher_index = lastI.getIntExtra("teacher_index", -1);
        setContentView(R.layout.activity_create_class_room);
        ET_name = findViewById(R.id.create_classroom_className);
        ET_code = findViewById(R.id.create_classroom_classCode);
        cancel = findViewById(R.id.create_classroom_button_cancel);
        create_classroom = findViewById(R.id.create_classroom_button_create);
        cancel.setOnClickListener(CancelButtonListener);
    }
}