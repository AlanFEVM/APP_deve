package com.example.learningapp.Data;

import java.io.Serializable;

public class AccountData implements Serializable {
    private String account_name;
    private String password;
    public AccountData(String a_n, String pw) {
        this.account_name = a_n;
        this.password = pw;
    }
    public String getAccount_name() {
        return account_name;
    }
    public String getPassword() {
        return password;
    }
}