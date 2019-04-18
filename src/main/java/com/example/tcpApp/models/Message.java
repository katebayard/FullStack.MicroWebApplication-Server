package com.example.tcpApp.models;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String channel;
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", channel='" + channel + '\'' +
                ", sender='" + sender + '\'' +
                ", timestamp=" + timestamp +
                ", messageContent='" + messageContent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                channel.equals(message.channel) &&
                sender.equals(message.sender) &&
                Objects.equals(timestamp, message.timestamp) &&
                messageContent.equals(message.messageContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, channel, sender, timestamp, messageContent);
    }
}
