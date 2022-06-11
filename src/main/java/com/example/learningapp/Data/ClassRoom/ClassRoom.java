package com.example.learningapp.Data.ClassRoom;

import com.example.learningapp.Data.my_Data;

import java.util.ArrayList;
import java.util.Random;
public class ClassRoom {
    String class_code;
    String course_name;
    int teacher_index;
    ArrayList<Integer> my_student;
    ArrayList<CheckIn> my_checkIn;

    public ClassRoom(Integer T, String code, String c_n) {
        teacher_index = T;
        class_code = code;
        course_name = c_n;
        my_student = new ArrayList<Integer>();
        my_checkIn = new ArrayList<>();
    }

    public void create_checkIn(String password, boolean has_password) {
        CheckIn new_check = new CheckIn(password, has_password);
        my_checkIn.add(new_check);
    }

    public ArrayList<Integer> getMy_student() {
        return my_student;
    }

    public String getClass_code() {
        return class_code;
    }

    public String getTeacher_name() {
        return my_Data.user_data.getTeacher(teacher_index).getName();
    }

    public String getCourse_name() {
        return course_name;
    }
    public int getTeacher_index() {
        return teacher_index;
    }
    public int get_student_num() {
        return my_student.size();
    }


    public void addStudent_By_student_index(int index) {
        my_student.add(index);
    }

    public int Random_pickup() {
        Random rand = new Random();
        int i = rand.nextInt(my_student.size());
        return my_student.get(i);
    }
}
