package com.example.learningapp.Data.UserDataBase;

import com.example.learningapp.Data.Message_pack.ClassRoomMessage;
import com.example.learningapp.Data.Message_pack.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Student extends my_Person {
    private String student_code;
    private HashMap<Integer,ClassRoomMessage> my_ClassRoom_Message;

    private ArrayList<Integer> class_room_array;

    public Student() {
        super();
        class_room_array = new ArrayList<>();
        my_ClassRoom_Message = new HashMap<>();
    }
    public void add_Message_queue(int classroom_index){
        ClassRoomMessage new_msg = new ClassRoomMessage();
        my_ClassRoom_Message.put(classroom_index,new_msg);
    }

    public ArrayList<Message> getClassRoomMessages(int index) {
        return Objects.requireNonNull(my_ClassRoom_Message.get(index)).getMessages();
    }

    public void create_Message(int classRoom_index, String title, String text) {
        my_ClassRoom_Message.get(classRoom_index).addMessage(title, text);
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

    public boolean is_AtClassRoom(int room_index) {
        for (int i = 0; i < this.class_room_array.size(); i++) {
            if (class_room_array.get(i) == room_index) {
                return true;
            }
        }
        return false;
    }

    public ClassRoomMessage getClassRoomMessage(int classRoomIndex) {
        return my_ClassRoom_Message.get(classRoomIndex);
    }
}
