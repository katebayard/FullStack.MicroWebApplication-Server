package com.example.tcpApp.models;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String channel;
    // convert to User object?
    private String sender;
    private Date timestamp;
    private String messageContent;

    private Message(){}

    public Message(String channel, String sender, Date timestamp, String messageContent) {
        this.channel = channel;
        this.sender = sender;
        this.timestamp = timestamp;
        this.messageContent = messageContent;
    }





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

}
