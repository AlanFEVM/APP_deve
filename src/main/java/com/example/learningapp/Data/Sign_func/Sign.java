package com.example.learningapp.Data.Sign_func;

import java.util.ArrayList;

public class Sign {
    String publisher;
    ArrayList<String> signer = new ArrayList<>();
    public void add_Signer (String name){
        signer.add(name);
    }
    public Sign(String pub,String lesson){
        publisher = pub;
    }
    public int current_signer(){
        return signer.size();
    }
}
