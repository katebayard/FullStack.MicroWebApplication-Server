package com.example.tcpApp.serviceTests;

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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

        assertThat(userService.connect(2l), is(user2));

        Assert.assertTrue(user2.getConnected());
    }

    @Test
    public void testLogin() {

    }

}
