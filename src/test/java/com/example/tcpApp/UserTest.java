package com.example.tcpApp;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserTest {

    @Test
    public void testClassSignatureAnnotations() {
        Assert.assertTrue(User.class.isAnnotationPresent(Entity.class));
    }

    @Test
    public void testCreateJson() throws JsonProcessingException {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setFirstName("Cara");
        user.setLastName("Eppes");
        user.setUsername("caraeppes");
        String expected = "{\"id\":null,\"firstName\":\"Cara\",\"lastName\":" +
                "\"Eppes\",\"username\":\"caraeppes\",\"connected\":false,\"channels\":[]}";

        // when
        String actual = objectMapper.writeValueAsString(user);

        // then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testgetId() {
        // given
        User user = new User();
        long expected  = 0l;
        user.setId(expected);

        // when
        long actual = user.getId();

        // then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testgetFirstName() {
        // given
        User user = new User();
        String expected = "Ashley";
        user.setFirstName(expected);

        // when
        String actual = user.getFirstName();

        // then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testgetLastName() {
        // given
        User user = new User();
        String expected = "Bloxom";
        user.setLastName(expected);

        // when
        String actual = user.getLastName();

        // then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testgetUsername() {
        // given
        User user = new User();
        String expected = "ashblox";
        user.setUsername(expected);

        // when
        String actual = user.getUsername();

        // then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testgetConnected() {
        // given
        User user = new User();
        user.setConnected(true);

        // when; then
        Assert.assertTrue(user.getConnected());
    }

    @Test
    public void testgetChannels() {
        // given
        User user = new User();
        Channel channel = new Channel("myChannel");
        Set<Channel> expected = new HashSet<>();
        expected.add(channel);
        user.setChannels(expected);

        // when
        Set<Channel> actual = user.getChannels();

        // then
        for (Channel channel1 : expected) {
            Assert.assertTrue(actual.contains(channel1));
        }
    }
}