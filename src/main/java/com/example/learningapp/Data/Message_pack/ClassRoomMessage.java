package com.example.learningapp.Data.Message_pack;

import java.util.ArrayList;

public class ClassRoomMessage {
    /*教室消息，存储在每个学生底下，由学生个体保存*/
    ArrayList<Message> my_Message;//消息列表
    public ClassRoomMessage(){
        my_Message = new ArrayList<>();
    }
    //获取消息
    public ArrayList<Message> getMessages(){
        return my_Message;
    }

    public void addMessage(String title,String text){
        //添加消息
        /*参数
        * title 标题
        * text 内容*/
        Message new_message = new Message(title,text);
        my_Message.add(new_message);
    }

    public int getCounts(){
        /*获取总消息数目*/
        return my_Message.size();
    }

    public int getUnreadMessagesCounts(){
        /*获取没有阅读的消息条目*/
        int result = 0;
        for(int i = 0; i<my_Message.size(); i++){
            if(!my_Message.get(i).isRead()){
                result++;
            }
        }
        return result;
    }
}
