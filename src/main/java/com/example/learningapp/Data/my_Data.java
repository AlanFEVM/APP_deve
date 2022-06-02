package com.example.learningapp.Data;

import com.example.learningapp.Data.ClassRoom.ClassRoom;
import com.example.learningapp.Data.UserDataBase.Teacher;
import com.example.learningapp.Data.UserDataBase.UserDataBase;

import java.util.ArrayList;

public class my_Data {
    public static UserDataBase user_data = null;
    public static ArrayList<ClassRoom> my_class_room = null;
    public static void initial_data_base() {
        user_data = new UserDataBase();
    }
    public static void creat_class_room(Teacher t,String code){
        ClassRoom new_classroom = new ClassRoom(t,code);
        my_class_room.add(new_classroom);
    }
}
