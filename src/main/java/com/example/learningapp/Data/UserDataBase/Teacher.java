package com.example.learningapp.Data.UserDataBase;

public class Teacher extends Person {
    /*Teacher 继承自 Person*/
    private String teacher_code; //教师编号
    private int classroom_index = -1; // 教师创建的教室在my_Data.my_class_room中的索引

    public int getClassroom_index() {
        //获取教室索引
        return classroom_index;
    }

    public void setClassroom_index(int classroom_index) {
        //设置教室索引
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
