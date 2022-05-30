package com.example.learningapp.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataBase implements Serializable {
    private ArrayList<Student> myStudents = new ArrayList<>();
    private ArrayList<Teacher> myTeachers = new ArrayList<>();

    public DataBase() {
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