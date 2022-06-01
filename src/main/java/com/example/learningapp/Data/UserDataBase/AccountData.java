package com.example.learningapp.Data.UserDataBase;

import java.io.Serializable;

public class AccountData implements Serializable {
    private final String account_name;
    private String password;

    public AccountData(String a_n, String pw) {
        this.account_name = a_n;
        this.password = pw;
    }
    public void reset_password(String s){
        password = s;
    }
    public String getAccount_name() {
        return account_name;
    }

    public String getPassword() {
        return password;
    }
}