package com.example.tcpApp.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob @Column(nullable = false)
    private String messageContents;
    @Column(nullable = false)
    private Date timeStamp = new Date();
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User sender;
    @Column(nullable = false)
    private Boolean isRead;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "messages_channel")
    private Channel channel;

    public Message() {
    }

    public Message(Long id, String messageContents, User sender, Boolean isRead) {
        this.id = id;
        this.messageContents = messageContents;
        this.sender = sender;
        this.isRead = isRead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageContents() {
        return messageContents;
    }

    public void setMessageContents(String messageContents) {
        this.messageContents = messageContents;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Channel getChannel() {
        return channel;
    }

    @Autowired
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", messageContents='" + messageContents + '\'' +
                ", timeStamp=" + timeStamp +
                ", sender=" + sender +
                ", isRead=" + isRead +
                '}';
    }
}
