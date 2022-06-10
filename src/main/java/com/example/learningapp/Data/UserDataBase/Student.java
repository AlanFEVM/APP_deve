package com.example.learningapp.Data.UserDataBase;

import com.example.learningapp.Data.Message_pack.ClassRoomMessage;
import com.example.learningapp.Data.Message_pack.Message;

import java.util.ArrayList;

public class Student extends my_Person {
    private String student_code;

    private ArrayList<ClassRoomMessage> my_ClassRoom_Message;
    private ArrayList<Integer> class_room_array;
    public Student() {
        super();
        class_room_array = new ArrayList<>();
        my_ClassRoom_Message = new ArrayList<>();
    }

    public ArrayList<Message> getAllMessage(int index){
        return my_ClassRoom_Message.get(index).getMessages();
    }

    public void create_Message(int classRoom_index,String title,String text){
        my_ClassRoom_Message.get(classRoom_index).addMessage(title,text);
    }
    public int getClassRoomMessageCount(int ClassRoomIndex){
        return my_ClassRoom_Message.get(ClassRoomIndex).getCounts();
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
