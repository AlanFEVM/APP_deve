package com.example.learningapp.Data.ClassRoom;

import com.example.learningapp.Data.my_Data;

import java.util.ArrayList;
import java.util.Random;

public class ClassRoom {
    String class_code;
    String class_name;
    int teacher_index;
    Sign sign;
    ArrayList<Integer> student_index_from_data = new ArrayList<>();

    public void create_sign (String lesson_name){
        String pub = my_Data.user_data.getTeacher(teacher_index).getName();
        Sign sign = new Sign(pub,lesson_name);
    }

    public ClassRoom(Integer T,String code) {
        class_code = code;
        class_name = my_Data.user_data.getTeacher(T).getName();
    }
    public void addStudent(int index){
        student_index_from_data.add(index);
    }
    public int Random_pickup(){
        Random rand = new Random();
        int i = rand.nextInt(student_index_from_data.size());
        return student_index_from_data.get(i);
    }
}
