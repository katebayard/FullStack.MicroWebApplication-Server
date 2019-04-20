package com.example.tcpApp.serviceTests;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.repositories.ChannelRepository;
import com.example.tcpApp.services.ChannelService;
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

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChannelServiceTests {

    @MockBean
    ChannelRepository channelRepository;

    @Autowired
    ChannelService channelService;

    @Before
    public void setup(){
        channelRepository = mock(ChannelRepository.class);
        channelService = new ChannelService(channelRepository);
    }

    @Test
    public void testCreate() {
        // Given
        Channel expected = new Channel();
        expected.setId(5L);
        expected.setChannelName("test");
        when(channelRepository.save(expected))
                .thenReturn(expected);

        // When
        Channel actual = channelService.create(expected);

        // Then
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testFindById() {
        // Given
        Channel expected = new Channel();
        expected.setId(1L);
        expected.setChannelName("test");
        when(channelRepository.getOne(1L))
                .thenReturn(expected);
        // When
        Channel actual = channelService.findById(1L);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindByChannelName() {
        Channel expected = new Channel();
        expected.setId(1L);
        expected.setChannelName("test");
        when(channelRepository.findByChannelName("test"))
                .thenReturn(expected);

        // When
        Channel actual = channelService.findByChannelName("test");

        // Then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void findAllTest() {
        // Given
        Channel channel1 = new Channel();
        channel1.setId(1L);
        Channel channel2 = new Channel();
        channel2.setId(2L);
        Iterable<Channel> expected = new ArrayList<>();
        ((ArrayList<Channel>) expected).add(channel1);
        ((ArrayList<Channel>) expected).add(channel2);
        when(channelRepository.findAll())
                .thenReturn((List<Channel>) expected);

        // When
        Iterable<Channel> actual = channelService.findAll();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addUserTest() {
        // Given
        Channel channel = new Channel();
        channel.setId(1L);
        User user = new User();
        Channel expected = channel;
        expected.getUsers().add(user);
        when(channelRepository.getOne(1L))
                .thenReturn(channel);
        when(channelRepository.save(channel))
            .thenReturn(expected);

        // When
        Channel actual = channelService.addUser(user, 1L);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeUserTest() {
        // Given
        Channel channel = new Channel();
        channel.setChannelName("test");
        Channel expected = channel;
        User user = new User();
        channel.getUsers().add(user);
        when(channelRepository.findByChannelName("test"))
                .thenReturn(channel);
        when(channelRepository.save(channel))
                .thenReturn(expected);

        // When
        Channel actual = channelService.removeUser(user, "test");

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteTest() {
        // Given
        Channel channel = new Channel();
        channel.setId(1L);
        when(channelRepository.getOne(1L))
                .thenReturn(channel);

        // When
        Boolean actual = channelService.delete(1L);

        // Then
        verify(channelRepository, times(1)).deleteById(1L);
        Assert.assertTrue(actual);
    }

}
