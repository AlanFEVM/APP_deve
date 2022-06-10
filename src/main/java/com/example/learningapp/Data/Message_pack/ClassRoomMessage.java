package com.example.learningapp.Data.Message_pack;

import java.util.ArrayList;

public class ClassRoomMessage {
    ArrayList<Message> my_Message;

    ClassRoomMessage(){
        my_Message = new ArrayList<>();
    }
    public ArrayList<Message> getMessages(){
        return my_Message;
    }
    public void addMessage(String title,String text){
        Message new_message = new Message(title,text);
    }
    public int getCounts(){
        return my_Message.size();
    }
    public int getUnreadMessagesCounts(){
        int result = 0;
        for(int i = 0; i<my_Message.size(); i++){
            if(!my_Message.get(i).isRead()){
                result++;
            }
        }
        return result;
    }
}
