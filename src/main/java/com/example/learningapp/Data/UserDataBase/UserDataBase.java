package com.example.learningapp.Data.UserDataBase;

import java.util.ArrayList;

//用户数据库模型
public class UserDataBase {
    /*用户数据信息，存放所有的Stduent对象和Teacher对象*/
    private static final ArrayList<Student> myStudents = new ArrayList<>();//存放所有学生信息
    private static final ArrayList<Teacher> myTeachers = new ArrayList<>();//存放所有教师信息

    public ArrayList<Student> getStudentArray() {
        return myStudents;
    }

    public UserDataBase() {
    }

    public boolean match_account(String account_name) {
        /*账号匹配函数
        * 参数：account_name 传入账号名称
        * 判断范围：Student 和 Teacher 的用户名
        * 作用：用于检测一个账号创建时是否会与其他账号名相同
        * 当匹配上某一个账号的时候则返回true
        * 如果没有匹配上某一个账号则返回false*/
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
        /*用于获取最后一个教师的索引，创建教师的时候会用到*/
        int i = myTeachers.size();
        return i - 1;
    }

    public int getStudent_last_index() {
        int i = myStudents.size();
        return i - 1;
    }

    public void addTeacher(Teacher T) {
        /*添加教师
        * 传入一个学生对象，该对象需要在外部完成构建*/
        myTeachers.add(T);
    }

    public void addStudent(Student S) {
        myStudents.add(S);
    }

    public Student getStudent(int index) {
        /*返回学生数组索引为index的学生对象*/
        return myStudents.get(index);
    }

    public Teacher getTeacher(int index) {
        return myTeachers.get(index);
    }

    public int matchTeacher(String account, String pwd) {
        /*用于登录检测，成功登录则返回学生数组索引，否则返回-1*/
        for (int i = 0; i < myTeachers.size(); i++) {
            if (myTeachers.get(i).getAccountData().getAccount_name().matches(account) && myTeachers.get(i).getAccountData().getPassword().matches(pwd)) {
                return i;
            }
        }
        return -1;
    }

    public int matchStudent(String account, String pwd) {
        /*用于登录检测，成功登录则返回教师素组索引，否则返回-1*/
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
        /*通过传入的 code 匹配学生并返回学生的数组索引*/
        for (int i = 0; i < myStudents.size(); i++) {
            if (myStudents.get(i).getStudent_code().matches(code)) {
                return i;
            }
        }
        return -1;
    }


    public ArrayList<Integer> getStudentIndexNotInClassRoom(int classroom_index) {
        /*返回一个学生数组，学生数组中所有的学生不在某个教室中*/
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