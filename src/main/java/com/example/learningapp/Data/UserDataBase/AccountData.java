package com.example.learningapp.Data.UserDataBase;

public class AccountData {
    /*用户数据，用于存储账号和密码，提供3个操作
    * 构造函数：生成一个用户信息
    * reset_password 重置密码
    * getAccount_name 用于获取用户名
    * getPassword 获取密码*/
    private final String account_name; // 用户数据是常量，不可变更
    private String password;

    public AccountData(String a_n, String pw) {
        this.account_name = a_n;
        this.password = pw;
    }

    public void reset_password(String s) {
        password = s;
    }

    public String getAccount_name() {
        return account_name;
    }

    public String getPassword() {
        return password;
    }
}