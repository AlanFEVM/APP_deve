package com.example.learningapp.Data.UserDataBase;

import java.util.ArrayList;

public abstract class Person {
    /*Person 类属于抽象类,只能创建Person的子类
    * 子类：Student Teacher*/
    private String name;
    private int age;
    private AccountData accountData;
    private boolean gender;// t -> male. f -> female
    public Person() {

    }

    public String getGender() {
        if (gender) {
            return "男";
        }
        return "女";
    }

    public Boolean getGenderBoolean() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return String.valueOf(age);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }

}