package com.example.learningapp.Data.ClassRoom;

import java.util.ArrayList;

public class CheckIn {
    String password;
    boolean has_password;
    ArrayList<Integer> student_check_status;

    CheckIn(String password, boolean has_password) {
        this.password = password;
        this.has_password = has_password;
        student_check_status = new ArrayList<>();
    }

    public boolean checkIn_add_student(int student_index, String password) {
        if (this.password.matches(password)) {
            student_check_status.add(student_index);
            return true;
        }
        return false;
    }
}
