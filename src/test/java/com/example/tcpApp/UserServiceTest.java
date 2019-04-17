package com.example.tcpApp;

import com.example.tcpApp.models.User;
import com.example.tcpApp.repositories.ChannelRepository;
import com.example.tcpApp.repositories.UserRepository;
import com.example.tcpApp.services.ChannelService;
import com.example.tcpApp.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepositoryMock;

    @MockBean
    private ChannelService channelServiceMock;

    @MockBean
    private ChannelRepository channelRepositoryMock;

//    @Test
//    public void testFindAll() {
//        List<User> users = new ArrayList<>();
//        User user1 = new User();
//        user1.setFirstName("Ash");
//        user1.setLastName("Bloxom");
//        user1.setUsername("ashblox");
//        User user2 = new User();
//        user2.setFirstName("Cara");
//        user2.setLastName("Eppes");
//        user2.setUsername("ceppes");
//        users.add(user1);
//        users.add(user2);
//
//        when(userRepositoryMock.findAll()).thenReturn(users);
//
//    }


}
