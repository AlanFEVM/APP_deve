package com.example.learningapp.Data.UserDataBase;

public class Teacher extends my_Person {
    private String teacher_code;
    private int classroom_index = -1;
    public int getClassroom_index() {
        return classroom_index;
    }
    public void setClassroom_index(int classroom_index) {
        this.classroom_index = classroom_index;
    }
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
