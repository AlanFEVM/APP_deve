package com.example.learningapp.Data;

import com.example.learningapp.Data.ClassRoom.ClassRoom;
import com.example.learningapp.Data.UserDataBase.UserDataBase;

import java.util.ArrayList;

public class my_Data {
    public static UserDataBase user_data = null; //这个数据库用域存放用户数据
    public static ArrayList<ClassRoom> my_class_room = null;// 这个数据库用于存放多个教室

    public static void initial_data_base() {
        user_data = new UserDataBase();
        my_class_room = new ArrayList<ClassRoom>();
    }//用户数据库初始化函数

    public static void create_class_room(Integer teacher_index, String course_name, String code) {//创建教室的函数，到时候在教师端调用
        ClassRoom new_classroom = new ClassRoom(teacher_index, code, course_name);
        my_class_room.add(new_classroom);
    }

    public static int getLastClassRoomIndex() {
        return my_class_room.size() - 1;
    }

    public static ClassRoom find_ClassRoomByTeacherClassRoomIndex(int i) {
        int ci = my_Data.user_data.getTeacher(i).getClassroom_index();
        return my_class_room.get(ci);
    }

    public static boolean checkClassRoomCodeValid(String code) {
        for (int i = 0; i < my_class_room.size(); i++) {
            if (my_class_room.get(i).getClass_code().matches(code)) {
                return false;
            }
        }
        return true;
    }
}
