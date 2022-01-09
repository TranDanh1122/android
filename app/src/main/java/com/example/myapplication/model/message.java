package com.example.myapplication.model;

public class message {
    public String messageText,gravity;

     public message(String messageText,String gravity){
         this.messageText=messageText;
         this.gravity=gravity;
     }
     public message(){

     }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
