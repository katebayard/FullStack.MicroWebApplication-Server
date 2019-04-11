package com.example.tcpApp.models;

public class InputMessage {

    private String sender;
    private String messageContent;

    public InputMessage(String sender, String messageContent) {
        this.sender = sender;
        this.messageContent = messageContent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
