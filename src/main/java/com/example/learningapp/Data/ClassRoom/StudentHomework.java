package com.example.learningapp.Data.ClassRoom;

public class StudentHomework {
    private String answer;
    private int student_index;
    private int score;
    private String comment;
    //初次填写答案调用这个方法
    public void fillanswer(int student_index, String answer){
        this.answer = answer;
        this.student_index = student_index;
    }
    //可供学生修改答案
    public void set_answer(String answer){
        this.student_index = student_index;
    }
    //获取学生索引
    public int getStudent_index(){
        return student_index;
    }
    //设置分数，评价
    public void set_score_comment(int score, String comment){
        this.score = score;
        this.comment = comment;
    }
}
