package com.example.learningapp.Data.Message_pack;

public class Message {
    /*单条消息
    * title : 标题
    * text : 文本内容
    * read: 是否已读*/
    private String title;
    private String text;
    private boolean read;
    private int message_Type;
    /*0- checkin
    * 1- homework
    * 2- questions*/
    private int message_Index;

    // constructor
    Message(String title, String text){
        this.title = title;
        this.text = text;
        read = false;
    }
    Message(String title, String text,int message_Type,int message_index){
        this.title = title;
        this.text = text;
        this.message_Type = message_Type;
        this.message_Index = message_index;
        read = false;
    }
    //getter 和 setter
    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setRead() {
        read = true;
    }

    public boolean isRead() {
        return read;
    }

    public void setMessage_direction(int type,int index){
        this.message_Type = type;
        this.message_Index = index;
    }
}
