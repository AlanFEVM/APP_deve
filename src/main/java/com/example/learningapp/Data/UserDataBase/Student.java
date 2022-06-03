package com.example.learningapp.Data.UserDataBase;

public class Student extends my_Person {
    private String student_code;

    public Student() {
        super();
    }
    public Student(String name,String age,String code ,boolean gender,AccountData accountData){
        this.setName(name);
        this.setGender(gender);
        this.setStudent_code(code);
        this.setAge(Integer.parseInt(age));
        this.setAccountData(accountData);
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }
}
