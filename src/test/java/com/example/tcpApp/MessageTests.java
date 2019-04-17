package com.example.tcpApp;

import com.example.tcpApp.models.Message;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class MessageTests {

    private Long testId = 101L;
    private String testChannel = "default";
    private String testSender = "Cara";
    private Date testTimestamp = new Date(2019, 03, 17, 9, 12, 30);
    private String testMessageContent = "testing";
    private String expectedMessage = "Message{id=null, channel='default', sender='Cara', timestamp=Thu Apr 17 09:12:30 EDT 3919, messageContent='testing'}";


    @Test
    public void messageConstructorTest() {
        //When
        Message testMessage = new Message(testChannel, testSender, testTimestamp, testMessageContent);
        String actualMessage = testMessage.toString();

        //Then
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void setAndGetIdTest() {
        //Given
        Message testMessage = new Message(testChannel, testSender, testTimestamp, testMessageContent);

        //When
        Assert.assertNull(testMessage.getId());
        testMessage.setId(testId);
        Long actualId = testMessage.getId();

        //Then
        Assert.assertEquals(testId, actualId);
    }

    @Test
    public void setAndGetChannelTest() {
        //Given
        Message testMessage = new Message(testChannel, testSender, testTimestamp, testMessageContent);

        //When
        Assert.assertEquals(testChannel, testMessage.getChannel());
        String newChannel = "cohort5.0";
        testMessage.setChannel(newChannel);
        String actualChannel = testMessage.getChannel();

        //Then
        Assert.assertEquals(newChannel, actualChannel);
    }



}
