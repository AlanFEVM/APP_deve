package com.example.learningapp.Data.ClassRoom;

import java.util.ArrayList;

public class CheckIn {
    /*签到基本数据结构
    * password 签到密码
    * has_password 是否含有签到密码
    * student_chek_status 教室中签到的学生数组下标*/
    String password;
    boolean has_password;
    ArrayList<Integer> student_check_status;

    CheckIn(String password, boolean has_password) {
        this.password = password;
        this.has_password = has_password;
        student_check_status = new ArrayList<>();
    }
    public boolean has_password(){
        return has_password;
    }
    public boolean checkIn_add_student(int student_index, String password) {
        /*签到动作，完成返回ture，失败返回false*/
        if (this.password.matches(password)) {
            student_check_status.add(student_index);
            return true;
        }
        return false;
    }
    public boolean is_student_have_checked(int student_index){
        return student_check_status.contains((Integer)student_index);
    }
}
