package com.example.learningapp.Data.UserDataBase;

import java.util.ArrayList;

public class Student extends my_Person {
    private String student_code;
    private ArrayList<Integer> class_room_array;
    public Student() {
        super();
        class_room_array = new ArrayList<Integer>();
    }
    public Student(String name,String age,String code ,boolean gender,AccountData accountData){
        super();
        this.setName(name);
        this.setGender(gender);
        this.setStudent_code(code);
        this.setAge(Integer.parseInt(age));
        this.setAccountData(accountData);
        class_room_array = new ArrayList<Integer>();
    }

    public String getStudent_code() {
        return student_code;
    }

    public ArrayList<Integer> getClass_room_array() {
        return class_room_array;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public boolean add_classRoom(int room_index){
        for(int i = 0; i < this.class_room_array.size();i++){
            if(class_room_array.get(i) == room_index){
                return false;
            }
        }
        class_room_array.add(room_index);
        return true;
    }
    public boolean is_AtClassRoom(int room_index){
        for(int i = 0; i < this.class_room_array.size();i++){
            if(class_room_array.get(i) == room_index){
                return true;
            }
        }
        return false;
    }
}
