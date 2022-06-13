package com.example.learningapp.Data.Message_pack;

public class Message {
    /*单条消息
    * title : 标题
    * text : 文本内容
    * read: 是否已读*/
    private String title;
    private String text;
    private boolean read;

    // constructor
    Message(String title, String text){
        this.title = title;
        this.text = text;
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
}
