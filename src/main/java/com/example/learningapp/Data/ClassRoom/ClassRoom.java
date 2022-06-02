package com.example.learningapp.Data.ClassRoom;

import com.example.learningapp.Data.UserDataBase.Student;
import com.example.learningapp.Data.UserDataBase.Teacher;
import com.example.learningapp.R;

import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("SuspiciousListRemoveInLoop")
public class ClassRoom {
    String class_code;
    String class_name;
    Teacher t;
    Sign sign;
    ArrayList<Student> class_student = new ArrayList<>();

    public void create_sign (String lesson_name){
        String pub = t.getName();
        Sign sign = new Sign(pub,lesson_name);
    }

    public ClassRoom(Teacher T,String code) {
        class_code = code;
        class_name = T.getName();
    }

    public void add_Student(Student t) {
        class_student.add(t);
    }

    public void remove_Student_by_code (String code){
        for(int i = 0; i <class_student.size();i++){
            if(class_student.get(i).getStudent_code().matches(code)){
                class_student.remove(i);
            }
        }
    }
    //随机点名
    public Student random_pick(){
        Random rand = new Random();
        int i = rand.nextInt(class_student.size());
        return class_student.get(i);
    }
}
