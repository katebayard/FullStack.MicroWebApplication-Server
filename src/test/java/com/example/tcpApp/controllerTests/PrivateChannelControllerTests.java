package com.example.tcpApp.controllerTests;

import com.example.tcpApp.controllers.PrivateChannelController;
import com.example.tcpApp.models.PrivateChannel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.services.PrivateChannelService;
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
public class PrivateChannelControllerTests {

    @MockBean
    private PrivateChannelService privateChannelService;

    private PrivateChannelController privateChannelController;

    @Before
    public void setup() {
        this.privateChannelController = new PrivateChannelController(privateChannelService);
    }

    @Test
    public void testCreate() {
        // Given
        HttpStatus expected = HttpStatus.CREATED;
        PrivateChannel expectedPrivateChannel = new PrivateChannel();
        BDDMockito
                .given(privateChannelService.create(expectedPrivateChannel))
                .willReturn(expectedPrivateChannel);

        // When
        ResponseEntity<PrivateChannel> response = privateChannelController.createPrivateChannel(expectedPrivateChannel);
        HttpStatus actual = response.getStatusCode();
        PrivateChannel actualPrivateChannel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedPrivateChannel, actualPrivateChannel);
    }


    @Test
    public void testFindById() {
        // Given
        Long privateChannelId = 10L;
        HttpStatus expected = HttpStatus.OK;
        PrivateChannel expectedPrivateChannel = new PrivateChannel();
        expectedPrivateChannel.setId(privateChannelId);
        BDDMockito.
                given(privateChannelService.findById(privateChannelId))
                .willReturn(expectedPrivateChannel);

        // When
        ResponseEntity<PrivateChannel> response = privateChannelController.findById(privateChannelId);
        HttpStatus actual = response.getStatusCode();
        PrivateChannel actualPrivateChannel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedPrivateChannel, actualPrivateChannel);
    }

    @Test
    public void testFindByPrivateChannelName() {
        // Given
        String name = "chatter";
        HttpStatus expected = HttpStatus.OK;
        PrivateChannel expectedPrivateChannel = new PrivateChannel();
        expectedPrivateChannel.setChannelName(name);
        BDDMockito.
                given(privateChannelService.findByPrivateChannelName(name))
                .willReturn(expectedPrivateChannel);

        // When
        ResponseEntity<PrivateChannel> response = privateChannelController.findByName(name);
        HttpStatus actual = response.getStatusCode();
        PrivateChannel actualPrivateChannel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedPrivateChannel, actualPrivateChannel);
    }

    @Test
    public void findAllTest() {
        // Given
        PrivateChannel privateChannel1 = new PrivateChannel();
        PrivateChannel privateChannel2 = new PrivateChannel();
        Iterable<PrivateChannel> expectedPrivateChannels = new ArrayList<>();
        ((ArrayList<PrivateChannel>) expectedPrivateChannels).add(privateChannel1);
        ((ArrayList<PrivateChannel>) expectedPrivateChannels).add(privateChannel2);
        HttpStatus expected = HttpStatus.OK;
        BDDMockito.
                given(privateChannelService.findAll())
                .willReturn(expectedPrivateChannels);

        // When
        ResponseEntity<Iterable<PrivateChannel>> response = privateChannelController.findAll();
        HttpStatus actual = response.getStatusCode();
        Iterable<PrivateChannel> actualPrivateChannels = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedPrivateChannels, actualPrivateChannels);
    }

    @Test
    public void addUserTest() {
        // Given
        PrivateChannel expectedPrivateChannel = new PrivateChannel();
        Long privateChannelId = 10L;
        expectedPrivateChannel.setId(privateChannelId);
        User user = new User();
        List<User> users = new ArrayList<>();
        users.add(user);
        expectedPrivateChannel.setUsers(users);
        HttpStatus expected = HttpStatus.OK;
        BDDMockito.
                given(privateChannelService.addUser(user, privateChannelId))
                .willReturn(expectedPrivateChannel);

        // When
        ResponseEntity<PrivateChannel> response = privateChannelController.addUser(user, privateChannelId);
        HttpStatus actual = response.getStatusCode();
        PrivateChannel actualPrivateChannel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedPrivateChannel, actualPrivateChannel);
    }

    @Test
    public void removeUserTest() {
        // Given
        PrivateChannel privateChannel = new PrivateChannel();
        String privateChannelName = "chatter";
        privateChannel.setChannelName(privateChannelName);
        PrivateChannel expectedPrivateChannel = privateChannel;
        User user = new User();
        privateChannel.getUsers().add(user);
        HttpStatus expected = HttpStatus.OK;
        BDDMockito.
                given(privateChannelService.removeUser(user, privateChannelName))
                .willReturn(expectedPrivateChannel);

        // When
        ResponseEntity<PrivateChannel> response = privateChannelController.removeUser(user, privateChannelName);
        HttpStatus actual = response.getStatusCode();
        PrivateChannel actualPrivateChannel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedPrivateChannel, actualPrivateChannel);
    }

    @Test
    public void deleteTest() {
        // Given
        PrivateChannel privateChannel = new PrivateChannel();
        Long id = 3L;
        privateChannel.setId(id);
        HttpStatus expected = HttpStatus.NOT_FOUND;
        BDDMockito
                .given(privateChannelService.delete(id))
                .willReturn(true);

        // When
        ResponseEntity<Boolean> response = privateChannelController.delete(id);
        HttpStatus actual = response.getStatusCode();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(response.getBody());
    }
}
