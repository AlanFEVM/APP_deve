package com.example.learningapp.Data.UserDataBase;

import java.io.Serializable;

public class Teacher extends my_Person implements Serializable {
    private String teacher_code;

    public Teacher() {
        super();
    }

    public String getTeacher_code() {
        return teacher_code;
    }

    public void setTeacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
    }
}
