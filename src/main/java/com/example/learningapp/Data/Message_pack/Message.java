package com.example.learningapp.Data.Message_pack;

public class Message {
    private String title;
    private String text;
    private boolean read;

    Message(String title, String text){
        this.title = title;
        this.text = text;
        read = false;
    }
    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setRead(){
        read = true;
    }

    public boolean isRead(){
        return read;
    }
}
