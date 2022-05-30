package com.example.learningapp.Data;

import java.io.Serializable;

public class Student extends my_Person implements Serializable {
    private String student_code;
    public Student() {
        super();
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }
}
