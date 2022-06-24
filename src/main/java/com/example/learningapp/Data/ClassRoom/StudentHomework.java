package com.example.learningapp.Data.ClassRoom;

public class StudentHomework {
    private String answer;
    private String buffer_answer;
    private int student_index;
    private int score;
    private String comment;
    private boolean commented;
    private boolean submited;
    public void setSubmited(){
        this.submited = true;
    }
    public boolean isSubmited(){
        return submited;
    }
    public StudentHomework(int index){
        buffer_answer = "";
        this.student_index = index;
        commented = false;
        submited = false;
    }

    public boolean isCommented(){
        return commented;
    }
    //提交答案前答案调用这个方法用于临时存储数据
    public void set_buffer_answer(String answer){
        buffer_answer = answer;
    }
    public String get_buffer_answer(){
        return buffer_answer;
    }

    //可供学生修改答案
    public void set_answer(String answer){
        this.answer = answer;
    }
    public String get_answer(){
        return answer;
    }
    //获取学生索引
    public int getStudent_index(){
        return student_index;
    }
    //设置分数，评价
    public void set_score_comment(int score, String comment){
        this.score = score;
        this.comment = comment;
        commented = true;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }
}
