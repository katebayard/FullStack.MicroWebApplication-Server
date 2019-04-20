package com.example.tcpApp.serviceTests;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.repositories.ChannelRepository;
import com.example.tcpApp.repositories.UserRepository;
import com.example.tcpApp.services.ChannelService;
import com.example.tcpApp.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepositoryMock;

    @MockBean
    private ChannelService channelServiceMock;

    @MockBean
    private ChannelRepository channelRepositoryMock;

    private User user1;
    private User user2;
    private List<User> users;
    private Channel channel;

    @Before
    public void setUp() {
        users = new ArrayList<>();
        user1 = new User();
        user1.setId(1l);
        user1.setFirstName("Ash");
        user1.setLastName("Bloxom");
        user1.setUsername("ashblox");
        user2 = new User();
        user2.setId(2l);
        user2.setFirstName("Cara");
        user2.setLastName("Eppes");
        user2.setUsername("ceppes");
        users.add(user1);
        users.add(user2);

        channel = new Channel();
        channel.setId(0l);
        channel.setChannelName("Test");
    }

    @Test
    public void testFindAll() {
        when(userRepositoryMock.findAll()).thenReturn(users);

        assertThat(userService.findAll(), is(users));

        verify(userRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testFindById() {
        when(userRepositoryMock.getOne(1l)).thenReturn(user1);

        assertThat(userService.findById(1l), is(user1));

        verify(userRepositoryMock, times(1)).getOne(1l);
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testFindByUsername() {
        when(userRepositoryMock.findByUsername("ceppes")).thenReturn(user2);

        assertThat(userService.findByUsername("ceppes"), is(user2));

        verify(userRepositoryMock, times(1)).findByUsername("ceppes");
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testCreateUserSuccessful() throws Exception {
        User user3 = new User();
        user3.setFirstName("Kate");
        user3.setLastName("Bayard");
        user3.setUsername("kbayard");

        when(userRepositoryMock.findByUsername(user3.getUsername())).thenReturn(null);
        when(userRepositoryMock.save(user3)).thenReturn(user3);

        assertThat(userService.create(user3), is(user3));

        verify(userRepositoryMock, times(1)).findByUsername(user3.getUsername());
        verify(userRepositoryMock, times(1)).save(user3);
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test(expected = Exception.class)
    public void testCreateUserUnsuccessful() throws Exception {
        when(userRepositoryMock.findByUsername(user1.getUsername())).thenReturn(user1);

        userService.create(user1);
    }

    @Test
    public void testDelete() {
        User user3 = new User();
        user3.setId(3l);
        users.add(user3);

        assertThat(userService.delete(3l), is(true));

        verify(userRepositoryMock, times(1)).deleteById(3l);
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testConnect() {
        when(userRepositoryMock.getOne(2l)).thenReturn(user2);
        when(userRepositoryMock.save(user2)).thenReturn(user2);
        user2.setConnected(false);

        assertThat(userService.connect(2l), is(user2));

        Assert.assertTrue(user2.getConnected());

        verify(userRepositoryMock, times(1)).getOne(2l);
        verify(userRepositoryMock, times(1)).save(user2);
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testLogin() {
        when(userRepositoryMock.findByUsername("ashblox")).thenReturn(user1);
        when(userRepositoryMock.save(user1)).thenReturn(user1);
        user1.setConnected(false);

        assertThat(userService.login("ashblox"), is(user1));

        Assert.assertTrue(user1.getConnected());

        verify(userRepositoryMock, times(1)).findByUsername("ashblox");
        verify(userRepositoryMock, times(1)).save(user1);
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testDisconnect() {
        when(userRepositoryMock.getOne(2l)).thenReturn(user2);
        when(userRepositoryMock.save(user2)).thenReturn(user2);
        user2.setConnected(true);

        assertThat(userService.disconnect(2l), is(user2));

        Assert.assertFalse(user2.getConnected());

        verify(userRepositoryMock, times(1)).getOne(2l);
        verify(userRepositoryMock, times(1)).save(user2);
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testLogout() {
        when(userRepositoryMock.findByUsername("ashblox")).thenReturn(user1);
        when(userRepositoryMock.save(user1)).thenReturn(user1);
        user1.setConnected(true);

        assertThat(userService.logout("ashblox"), is(user1));

        Assert.assertFalse(user1.getConnected());

        verify(userRepositoryMock, times(1)).findByUsername("ashblox");
        verify(userRepositoryMock, times(1)).save(user1);
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testJoinChannel() {
        when(userRepositoryMock.getOne(2l)).thenReturn(user2);
        when(channelRepositoryMock.getOne(0l)).thenReturn(channel);
        when(userRepositoryMock.save(user2)).thenReturn(user2);
        int expectedSize = user2.getChannels().size() + 1;

        assertThat(userService.joinChannel(2l, 0l), is(user2));

        Assert.assertEquals(expectedSize, user2.getChannels().size());
        Assert.assertTrue(user2.getChannels().contains(channel));

        verify(userRepositoryMock, times(1)).getOne(2l);
        verify(channelRepositoryMock, times(1)).getOne(0l);
        verify(channelServiceMock, times(1)).addUser(user2, 0l);
        verify(userRepositoryMock, times(1)).save(user2);
        verifyNoMoreInteractions(userRepositoryMock);
        verifyNoMoreInteractions(channelRepositoryMock);
        verifyNoMoreInteractions(channelServiceMock);
    }

    @Test
    public void testJoinChannelByName() {
        when(userRepositoryMock.findByUsername("ashblox")).thenReturn(user1);
        when(channelRepositoryMock.findByChannelName("Test")).thenReturn(channel);
        when(userRepositoryMock.save(user1)).thenReturn(user1);
        int expectedSize = user1.getChannels().size() + 1;

        assertThat(userService.joinChannelByName("ashblox", "Test"), is(user1));

        Assert.assertEquals(expectedSize, user1.getChannels().size());
        Assert.assertTrue(user1.getChannels().contains(channel));

        verify(userRepositoryMock, times(1)).findByUsername("ashblox");
        verify(channelRepositoryMock, times(1)).findByChannelName("Test");
        verify(channelServiceMock, times(1)).addUser(user1, 0l);
        verify(userRepositoryMock, times(1)).save(user1);
        verifyNoMoreInteractions(userRepositoryMock);
        verifyNoMoreInteractions(channelRepositoryMock);
        verifyNoMoreInteractions(channelServiceMock);
    }

    @Test
    public void testLeaveChannel() {
        when(userRepositoryMock.findByUsername("ceppes")).thenReturn(user2);
        when(channelRepositoryMock.findByChannelName("Test")).thenReturn(channel);
        user2.getChannels().add(channel);
        when(userRepositoryMock.save(user2)).thenReturn(user2);
        int expectedSize = user2.getChannels().size() - 1;

        assertThat(userService.leaveChannel("ceppes", "Test"), is(user2));

        Assert.assertEquals(expectedSize, user2.getChannels().size());
        Assert.assertFalse(user2.getChannels().contains(channel));

        verify(userRepositoryMock, times(1)).findByUsername("ceppes");
        verify(channelRepositoryMock, times(1)).findByChannelName("Test");
        verify(channelServiceMock, times(1)).removeUser(user2, "Test");
        verify(userRepositoryMock, times(1)).save(user2);
        verifyNoMoreInteractions(userRepositoryMock);
        verifyNoMoreInteractions(channelRepositoryMock);
        verifyNoMoreInteractions(channelServiceMock);
    }

    @Test
    public void testDeleteAll() {
        assertThat(userService.deleteAll(), is(true));

        verify(userRepositoryMock, times(1)).deleteAll();
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testFindAllByChannels() {
        when(channelServiceMock.findById(0l)).thenReturn(channel);
        when(userRepositoryMock.findAllByChannels(channel, PageRequest.of(0, 20))).thenReturn(users);

        assertThat(userService.findAllByChannels(0l, PageRequest.of(0, 20)), is(users));

        verify(channelServiceMock, times(1)).findById(0l);
        verify(userRepositoryMock, times(1)).findAllByChannels(channel, PageRequest.of(0, 20));
        verifyNoMoreInteractions(channelServiceMock);
        verifyNoMoreInteractions(userRepositoryMock);
    }

}
