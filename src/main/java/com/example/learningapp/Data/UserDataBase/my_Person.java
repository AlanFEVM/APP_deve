package com.example.learningapp.Data.UserDataBase;

import java.io.Serializable;

public class my_Person implements Serializable{
    private String name;
    private int age;
    private AccountData accountData; // t -> male. f -> female

    public String getGender() {
        if(gender){
            return "男";
        }
        return "女";
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    private boolean gender;

    public my_Person(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
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