package com.example.learningapp.Data.ClassRoom;

import java.util.ArrayList;

public class HomeworkList {
    private String title;
    private String content;
    private ArrayList<StudentHomework> student_answer;

    public int getAnswerCounts(){
        return student_answer.size();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public HomeworkList(String title, String content) {
        this.title = title;
        this.content = content;
        student_answer = new ArrayList<>();
    }

    //通过学生索引来获取学生作业
    public StudentHomework getHomeworkByStudentIndex(int student_index) {
        for (int i = 0; i < student_answer.size(); i++) {
            if (student_answer.get(i).getStudent_index() == student_index) {
                return student_answer.get(i);
            }
        }
        return null;
    }

    //设置答案，由学生端调用
    public void set_Answer(int student_index, String answer) {
        for (int i = 0; i < student_answer.size(); i++) {
            if (student_answer.get(i).getStudent_index() == student_index) {
                student_answer.get(i).set_answer(answer);
                return;
            }
        }
        StudentHomework studentHomework = new StudentHomework();
        studentHomework.fillanswer(student_index, answer);
        student_answer.add(studentHomework);
    }

    //由教室端调用
    public void set_score_coment(int student_index, int score, String coment) {
        getHomeworkByStudentIndex(student_index).set_score_comment(score, coment);
    }
}
