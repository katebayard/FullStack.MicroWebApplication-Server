package com.example.tcpApp.modelTests;

import com.example.tcpApp.models.Message;
import org.junit.Assert;
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

    @Test
    public void setAndGetSenderTest() {
        //Given
        Message testMessage = new Message(testChannel, testSender, testTimestamp, testMessageContent);

        //When
        Assert.assertEquals(testSender, testMessage.getSender());
        String newSender = "Kate";
        testMessage.setSender(newSender);
        String actualSender = testMessage.getSender();

        //Then
        Assert.assertEquals(newSender, actualSender);
    }

    @Test
    public void setAndGetTimestampTest() {//Given
        Message testMessage = new Message(testChannel, testSender, null, testMessageContent);

        //When
        Assert.assertNull(testMessage.getTimestamp());
        Date newTimestamp = testTimestamp;
        testMessage.setTimestamp(newTimestamp);
        Date actualTimestamp = testMessage.getTimestamp();

        //Then
        Assert.assertEquals(newTimestamp, actualTimestamp);
    }

    @Test
    public void setAndGetMessageContent() {
        //Given
        Message testMessage = new Message(testChannel, testSender, testTimestamp, testMessageContent);

        //When
        Assert.assertEquals(testMessageContent, testMessage.getMessageContent());
        String newMessageContent = "testing again";
        testMessage.setMessageContent(newMessageContent);
        String actualMessageContent = testMessage.getMessageContent();

        //Then
        Assert.assertEquals(newMessageContent, actualMessageContent);
    }

    @Test
    public void toStringTest() {
        //Given
        Message testMessage = new Message(testChannel, testSender, testTimestamp, testMessageContent);

        //When
        String actualString = testMessage.toString();

        //Then
        Assert.assertEquals(expectedMessage, actualString);
    }

    @Test
    public void equalsTest() {
        //Given
        Message testMessage1 = new Message(testChannel, testSender, testTimestamp, testMessageContent);
        Message testMessage2 = new Message(testChannel, testSender, testTimestamp, testMessageContent);
        boolean expected = true;

        //When
        boolean actual = testMessage1.equals(testMessage2);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void hashCodeTest() {
        //Given
        Message testMessage1 = new Message(testChannel, testSender, testTimestamp, testMessageContent);
        int expectedHash = 1939872569;
        //When
        int actualHash = testMessage1.hashCode();
        //Then
        Assert.assertEquals(expectedHash, actualHash);
    }
}
