package com.example.learningapp.Data.ClassRoom;

import com.example.learningapp.Data.my_Data;

import java.util.ArrayList;
import java.util.Random;
public class ClassRoom {
    /* 教室类，用于存放一个教室的所有信息，比如签到数据，作业数据，答疑解惑数据，
    还可以通过构建新信息数据来添加内容
    class_code 教室编号
    course_name 课程名称
    teacher_index 教师编号
    my_student 存储本教室的学生索引，并不是学生的数据信息，学生数据和教师数据都属于静态信息，使用my_Data.my_UserData进行存储
    my_checkIn 签到列表，只能由教师进行添加操作
    * */
    String class_code;
    String course_name;
    int teacher_index;
    ArrayList<Integer> my_student;
    ArrayList<CheckIn> my_checkIn;

    public ClassRoom(Integer T, String code, String c_n) {
        teacher_index = T;
        class_code = code;
        course_name = c_n;
        my_student = new ArrayList<>();
        my_checkIn = new ArrayList<>();
    }

    public ArrayList<CheckIn> findNotCheckedCheckIns(int student_index) {
        ArrayList<CheckIn> result = new ArrayList<>();
        for (int i = 0; i < my_checkIn.size(); i++) {
            if(!my_checkIn.get(i).is_student_have_checked(student_index)){
                result.add(my_checkIn.get(i));
            }
        }
        return result;
    }

    public void create_checkIn(String password, boolean has_password) {
        /*添加一个签到*/
        CheckIn new_check = new CheckIn(password, has_password);
        my_checkIn.add(new_check);
    }

    public ArrayList<Integer> getMy_student() {
        /*获取教室内的学生索引*/
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
        /*添加学生*/
        my_student.add(index);
    }

    public int Random_pickup() {
        /*随机点名，返回一个索引值，这个索引值用于 my_Data.user_data.myStudent*/
        Random rand = new Random();
        int i = rand.nextInt(my_student.size());
        return my_student.get(i);
    }
}
