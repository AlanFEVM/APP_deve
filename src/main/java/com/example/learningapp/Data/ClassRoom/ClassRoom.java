package com.example.learningapp.Data.ClassRoom;

import com.example.learningapp.Data.my_Data;

import java.util.ArrayList;
import java.util.Random;

public class ClassRoom {
    String class_code;
    String teacher_name;
    String course_name;
    int teacher_index;
    Sign sign;
    ArrayList<Integer> my_student;
    public ArrayList<Integer> getMy_student(){
        return my_student;
    }
    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getTeacher_index() {
        return teacher_index;
    }

    public void setTeacher_index(int teacher_index) {
        this.teacher_index = teacher_index;
    }

    public int get_student_num() {
        return my_student.size();
    }

    public void create_sign(String lesson_name) {
        String pub = my_Data.user_data.getTeacher(teacher_index).getName();
        Sign sign = new Sign(pub, lesson_name);
    }

    public ClassRoom(Integer T, String code, String c_n) {
        class_code = code;
        course_name = c_n;
        teacher_name = my_Data.user_data.getTeacher(T).getName();
        my_student = new ArrayList<Integer>();
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
