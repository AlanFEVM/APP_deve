package com.example.learningapp.Data.UserDataBase;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDataBase implements Serializable {
    private final ArrayList<Student> myStudents = new ArrayList<>();
    private final ArrayList<Teacher> myTeachers = new ArrayList<>();

    public UserDataBase() {
    }

    public boolean match_account(String account_name){
        //true if match a account
        for(int i = 0;i < myStudents.size(); i++){
            if(myStudents.get(i).getAccountData().getAccount_name().matches(account_name)){
                return true;
            }
        }
        for(int i = 0;i < myTeachers.size(); i++){
            if(myTeachers.get(i).getAccountData().getAccount_name().matches(account_name)){
                return true;
            }
        }
        return false;
    }
    public int getTeacher_last_index(){
        int i = myTeachers.size();
        return i - 1;
    }

    public int getStudent_last_index(){
        int i = myStudents.size();
        return i -1;
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

    public int findTeacher(String account, String pwd) {
        for (int i = 0; i < myTeachers.size(); i++) {
            if (myTeachers.get(i).getAccountData().getAccount_name().matches(account) && myTeachers.get(i).getAccountData().getPassword().matches(pwd)) {
                return i;
            }
        }
        return -1;
    }

    public int findStudent(String account, String pwd) {
        for (int i = 0; i < myStudents.size(); i++) {
            if (myStudents.get(i).getAccountData().getAccount_name().matches(account) && myStudents.get(i).getAccountData().getPassword().matches(pwd)) {
                return i;
            }
        }
        return -1;
    }
}