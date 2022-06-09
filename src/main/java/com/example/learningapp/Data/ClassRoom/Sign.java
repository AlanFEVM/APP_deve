package com.example.learningapp.Data.ClassRoom;

import java.util.ArrayList;

public class Sign {
    String publisher;
    String sign_pwd;
    ArrayList<Integer> signer = new ArrayList<>();

    public void add_Signer(int student_index) {
        signer.add(student_index);
    }

    public Sign(String pub, String pwd) {
        publisher = pub;
        sign_pwd = pwd;
    }

    public int current_signer() {
        return signer.size();
    }
}
