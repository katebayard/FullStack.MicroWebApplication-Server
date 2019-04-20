package com.example.tcpApp.modelTests;

import com.example.tcpApp.models.BaseChannel;
import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

public class ChannelTests {

    @Test
    public void testClassSignatureAnnotations(){
        Assert.assertTrue(Channel.class.isAnnotationPresent(Entity.class));
    }

    @Test
    public void testInstanceOf() {
        Assert.assertTrue(new Channel() instanceof BaseChannel);
    }

    @Test
    public void testCreateJson() throws JsonProcessingException {
        // Given
        String givenName = "chatter";
        ObjectMapper objectMapper = new ObjectMapper();
        Channel channel = new Channel();
        channel.setChannelName(givenName);
        String expected = "{\"id\":null,\"channelName\":\"chatter\"}";

        // When
        String actual = objectMapper.writeValueAsString(channel);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setAndGetIdTest(){
        // Given
        Long expected = 10L;
        Channel channel = new Channel();

        // When
        channel.setId(expected);
        Long actual = channel.getId();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setAndGetNameTest(){
        // Given
        String expected = "expected";
        Channel channel = new Channel();

        // When
        channel.setChannelName(expected);
        String actual = channel.getChannelName();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAndSetUsersTest(){
        // Given
        List<User> expected = new ArrayList<>();
        User user = new User();
        expected.add(user);
        Channel channel = new Channel();

        // When
        channel.setUsers(expected);
        List<User> actual = channel.getUsers();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void toStringTest(){
        // Given
        Channel channel = new Channel();
        channel.setId(5L);
        channel.setChannelName("test");
        String expected = "Channel{id=5, channelName='test', users=[]}";

        // When
        String actual = channel.toString();

        // Then
        Assert.assertEquals(expected, actual);
    }


}


