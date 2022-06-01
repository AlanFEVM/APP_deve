package com.example.learningapp.Data;

import com.example.learningapp.Data.UserDataBase.UserDataBase;

public class my_Data {
    public static UserDataBase user_data = null;
    public static void initial_data_base(){
        user_data = new UserDataBase();
    }
}
