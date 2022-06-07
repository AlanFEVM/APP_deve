package com.example.learningapp.Data.UserDataBase;

import java.util.ArrayList;

//用户数据库模型
public class UserDataBase {
    private static final ArrayList<Student> myStudents = new ArrayList<>();//存放所有学生信息
    private static final ArrayList<Teacher> myTeachers = new ArrayList<>();//存放所有教师信息

    public ArrayList<Student> getStudentArray() {
        return myStudents;
    }

    public UserDataBase() {
    }
    //下面是一些算法

    //这里进行用户名匹配操作，参数传递外部给定的用户名，内部进行匹配
    public boolean match_account(String account_name) {
        //用户名属于敏感型数据，因此要对教师数据库和学生数据库进行逐一匹配
        for (int i = 0; i < myStudents.size(); i++) {
            if (myStudents.get(i).getAccountData().getAccount_name().matches(account_name)) {
                return true;
            }
        }
        for (int i = 0; i < myTeachers.size(); i++) {
            if (myTeachers.get(i).getAccountData().getAccount_name().matches(account_name)) {
                return true;
            }
        }
        return false;
    }

    public int getTeacher_last_index() {
        int i = myTeachers.size();
        return i - 1;
    }

    public int getStudent_last_index() {
        int i = myStudents.size();
        return i - 1;
    }

    public void addTeacher(Teacher T) {
        myTeachers.add(T);
    }

    public void addStudent(Student S) {
        myStudents.add(S);
    }

    public Student getStudent(int index) {
        return myStudents.get(index);
    }

    public Teacher getTeacher(int index) {
        return myTeachers.get(index);
    }

    public int matchTeacher(String account, String pwd) {
        for (int i = 0; i < myTeachers.size(); i++) {
            if (myTeachers.get(i).getAccountData().getAccount_name().matches(account) && myTeachers.get(i).getAccountData().getPassword().matches(pwd)) {
                return i;
            }
        }
        return -1;
    }

    public int matchStudent(String account, String pwd) {
        for (int i = 0; i < myStudents.size(); i++) {
            if (myStudents.get(i).getAccountData().getAccount_name().matches(account) && myStudents.get(i).getAccountData().getPassword().matches(pwd)) {
                return i;
            }
        }
        return -1;
    }

    //检测教师号是否冲突
    public boolean check_T_code_valid(String code) {
        for (int i = 0; i < myTeachers.size(); i++) {
            if (myTeachers.get(i).getTeacher_code().matches(code)) {
                return false;
            }
        }
        return true;
    }

    //检测学号是否冲突
    public boolean check_S_code_valid(String code) {
        for (int i = 0; i < myStudents.size(); i++) {
            if (myStudents.get(i).getStudent_code().matches(code)) {
                return false;
            }
        }
        return true;
    }

    //通过学号查找学生
    public Student findStudentByCode(String code) {
        for (int i = 0; i < myStudents.size(); i++) {
            if (myStudents.get(i).getStudent_code().matches(code)) {
                return myStudents.get(i);
            }
        }
        return null;
    }

    public int findStudentIndexByCode(String code) {
        for (int i = 0; i < myStudents.size(); i++) {
            if (myStudents.get(i).getStudent_code().matches(code)) {
                return i;
            }
        }
        return -1;
    }

    //返回一个学生索引数组，但是该学生并不在某个教室中
    public ArrayList<Integer> getStudentIndexNotInClassRoom(int classroom_index) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < myStudents.size(); i++) {
            boolean atClassRoom = myStudents.get(i).is_AtClassRoom(classroom_index);
            if (!atClassRoom) {
                result.add(i);
            }
        }
        return result;
    }
}