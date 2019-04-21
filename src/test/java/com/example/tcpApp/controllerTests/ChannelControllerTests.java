package com.example.tcpApp.controllerTests;

import com.example.tcpApp.controllers.ChannelController;
import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.services.ChannelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChannelControllerTests {

    @MockBean
    private ChannelService channelService;

    private ChannelController channelController;

    @Before
    public void setup() {
        this.channelController = new ChannelController(channelService);
    }

    @Test
    public void testCreate() {
        // Given
        HttpStatus expected = HttpStatus.CREATED;
        Channel expectedChannel = new Channel();
        BDDMockito
                .given(channelService.create(expectedChannel))
                .willReturn(expectedChannel);

        // When
        ResponseEntity<Channel> response = channelController.createChannel(expectedChannel);
        HttpStatus actual = response.getStatusCode();
        Channel actualChannel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedChannel, actualChannel);
    }


    @Test
    public void testFindById() {
        // Given
        Long channelId = 10L;
        HttpStatus expected = HttpStatus.OK;
        Channel expectedChannel = new Channel();
        expectedChannel.setId(channelId);
        BDDMockito.
                given(channelService.findById(channelId))
                .willReturn(expectedChannel);

        // When
        ResponseEntity<Channel> response = channelController.findById(channelId);
        HttpStatus actual = response.getStatusCode();
        Channel actualChannel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedChannel, actualChannel);
    }

    @Test
    public void testFindByChannelName() {
        // Given
        String name = "chatter";
        HttpStatus expected = HttpStatus.OK;
        Channel expectedChannel = new Channel();
        expectedChannel.setChannelName(name);
        BDDMockito.
                given(channelService.findByChannelName(name))
                .willReturn(expectedChannel);

        // When
        ResponseEntity<Channel> response = channelController.findByName(name);
        HttpStatus actual = response.getStatusCode();
        Channel actualChannel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedChannel, actualChannel);
    }

    @Test
    public void findAllTest() {
        // Given
        Channel channel1 = new Channel();
        Channel channel2 = new Channel();
        Iterable<Channel> expectedChannels = new ArrayList<>();
        ((ArrayList<Channel>) expectedChannels).add(channel1);
        ((ArrayList<Channel>) expectedChannels).add(channel2);
        HttpStatus expected = HttpStatus.OK;
        BDDMockito.
                given(channelService.findAll())
                .willReturn(expectedChannels);

        // When
        ResponseEntity<Iterable<Channel>> response = channelController.findAll();
        HttpStatus actual = response.getStatusCode();
        Iterable<Channel> actualChannels = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedChannels, actualChannels);
    }

    @Test
    public void addUserTest() {
        // Given
        Channel expectedChannel = new Channel();
        Long channelId = 10L;
        expectedChannel.setId(channelId);
        User user = new User();
        List<User> users = new ArrayList<>();
        users.add(user);
        expectedChannel.setUsers(users);
        HttpStatus expected = HttpStatus.OK;
        BDDMockito.
                given(channelService.addUser(user, channelId))
                .willReturn(expectedChannel);

        // When
        ResponseEntity<Channel> response = channelController.addUser(user, channelId);
        HttpStatus actual = response.getStatusCode();
        Channel actualChannel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedChannel, actualChannel);
    }

    @Test
    public void removeUserTest() {
        // Given
        Channel channel = new Channel();
        String channelName = "chatter";
        channel.setChannelName(channelName);
        Channel expectedChannel = channel;
        User user = new User();
        channel.getUsers().add(user);
        HttpStatus expected = HttpStatus.OK;
        BDDMockito.
                given(channelService.removeUser(user, channelName))
                .willReturn(expectedChannel);

        // When
        ResponseEntity<Channel> response = channelController.removeUser(user, channelName);
        HttpStatus actual = response.getStatusCode();
        Channel actualChannel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedChannel, actualChannel);
    }

    @Test
    public void deleteTest() {
        // Given
        Channel channel = new Channel();
        Long id = 3L;
        channel.setId(id);
        HttpStatus expected = HttpStatus.NOT_FOUND;
        BDDMockito
                .given(channelService.delete(id))
                .willReturn(true);

        // When
        ResponseEntity<Boolean> response = channelController.delete(id);
        HttpStatus actual = response.getStatusCode();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(response.getBody());
    }
}
