package com.example.learningapp.Data;

import com.example.learningapp.Data.ClassRoom.ClassRoom;
import com.example.learningapp.Data.UserDataBase.Teacher;
import com.example.learningapp.Data.UserDataBase.UserDataBase;

import java.util.ArrayList;

public class my_Data {
    public static UserDataBase user_data = null; //这个数据库用域存放用户数据
    public static ArrayList<ClassRoom> my_class_room = null;// 这个数据库用于存放多个教室
    public static void initial_data_base() {
        user_data = new UserDataBase();
    }//用户数据库初始化函数

    public static void creat_class_room(Integer t,String code){//创建教室的函数，到时候在教师端调用
        ClassRoom new_classroom = new ClassRoom(t,code);
        my_class_room.add(new_classroom);
    }
}
