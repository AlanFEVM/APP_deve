package com.example.learningapp.Data.UserDataBase;

public class AccountData {
    private final String account_name; // 用户数据是常量，不可变更
    private String password;

    public AccountData(String a_n, String pw) { //构造函数
        this.account_name = a_n;
        this.password = pw;
    }

    //重新设置密码
    public void reset_password(String s) {
        password = s;
    }

    public String getAccount_name() {
        return account_name;
    }

    //返回密码（危险操作）
    public String getPassword() {
        return password;
    }
}