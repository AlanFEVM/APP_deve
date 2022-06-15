package com.example.learningapp.Data;

import com.example.learningapp.Data.ClassRoom.ClassRoom;
import com.example.learningapp.Data.UserDataBase.UserDataBase;

import java.util.ArrayList;

public class my_Data {
    /*数据库，内部存放两个静态变量，通过外部调用
    * */

    public static UserDataBase user_data = null; //存放所有的用户数据
    public static ArrayList<ClassRoom> my_class_room = null;// 存储所有的教室信息


    public static ArrayList<Integer> findClassRoom_index_that_Student_is_not_in (ArrayList<Integer> classRoom_Index_from_Student){
        /*
        * 函数作用：返回学生未加入的教室索引值
        * 参数：classRoom_Index_from_Student 由 Student 对象传递进来
        * */
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < my_class_room.size();i++){
            result.add(i);
        }
        for (int i = 0; i < classRoom_Index_from_Student.size();i++){
            result.remove(classRoom_Index_from_Student.get(i));
        }
        return result;
    }

    public static void initial_data_base() {
        //用户数据库初始化函数
        user_data = new UserDataBase();
        my_class_room = new ArrayList<>();
    }

    public static void create_class_room(Integer teacher_index, String course_name, String code) {
        /* 创建一个教师
        * 参数列表
        * teacher_index: 教师编号
        * course_name: 课程名称
        * code: 教室编号*/
        ClassRoom new_classroom = new ClassRoom(teacher_index, code, course_name);
        my_class_room.add(new_classroom);
    }

    public static int getLastClassRoomIndex() {
        //获取最后一个教室的索引
        return my_class_room.size() - 1;
    }

    public static ClassRoom find_ClassRoomByTeacherIndex(int i) {
        //找到教师创建的教室
        int ci = my_Data.user_data.getTeacher(i).getClassroom_index();
        return my_class_room.get(ci);
    }

    public static boolean checkClassRoomCodeValid(String code) {
        //检测教室编号是否可用
        for (int i = 0; i < my_class_room.size(); i++) {
            if (my_class_room.get(i).getClass_code().matches(code)) {
                return false;
            }
        }
        return true;
    }

    public static int findClassRoomByCode(String toString) {
        //通过教室编号寻找教室（返回教室索引）
        for(int i = 0; i < my_class_room.size(); i++){
            if (my_class_room.get(i).getClass_code().matches(toString)){
                return i;
            }
        }
        return -1;
    }
}
