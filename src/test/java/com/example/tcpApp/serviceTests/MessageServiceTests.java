package com.example.tcpApp.serviceTests;

import com.example.tcpApp.controllers.MessageController;
import com.example.tcpApp.models.Message;
import com.example.tcpApp.services.MessageService;
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
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTests {

    @MockBean
    private MessageService service;

    private MessageController controller;

    @Before
    public void setup() {
        this.controller = new MessageController(service);
//        this.controller = new MessageController();
    }

    @Test
    public void testCreate() {
        //Given
        HttpStatus expected = HttpStatus.CREATED;
        Message expectedMessage = new Message("default", "Cara", new Date(), "testing");
        BDDMockito
                .given(service.create(expectedMessage))
                .willReturn(expectedMessage);

        //When
        ResponseEntity<Message> response = controller.sendMessage(expectedMessage);
        HttpStatus actual = response.getStatusCode();
        Message actualMessage = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedMessage, actualMessage);

    }

    @Test
    public void testFindAll() {
        //Given
        Message message1 = new Message("default", "Cara", new Date(), "testing");
        Message message2 = new Message("default", "Kate", new Date(), "testing2");

        List<Message> expectedMessages = new ArrayList<>();
        expectedMessages.add(message1);
        expectedMessages.add(message2);
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(service.findAll())
                .willReturn(expectedMessages);

        //When
        ResponseEntity<Iterable<Message>> response = controller.findAll();
        HttpStatus actual = response.getStatusCode();
        Iterable<Message> actualMessages = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedMessages, actualMessages);

    }

    @Test
    public void testFindAllByChannel() {
        //Given
        Message message1 = new Message("default", "Cara", new Date(), "testing");
        Message message2 = new Message("default", "Kate", new Date(), "testing2");
        Message message3 = new Message("channel2", "Kate", new Date(), "testing3");

        List<Message> expectedMessages = new ArrayList<>();
        expectedMessages.add(message1);
        expectedMessages.add(message2);
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(service.findAllByChannel("default", null))
                .willReturn(expectedMessages);

        //When
        ResponseEntity<List<Message>> response = controller.findAllByChannel("default", null);
        HttpStatus actual = response.getStatusCode();
        Iterable<Message> actualMessages = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedMessages, actualMessages);

    }


    @Test
    public void testFindBySender() {
        //Given
        Message message1 = new Message("default", "Cara", new Date(), "testing");
        Message message2 = new Message("default", "Kate", new Date(), "testing2");
        Message message3 = new Message("channel2", "Kate", new Date(), "testing3");

        List<Message> expectedMessages = new ArrayList<>();
        expectedMessages.add(message2);
        expectedMessages.add(message3);
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(service.findBySender("Kate", null))
                .willReturn(expectedMessages);

        //When
        ResponseEntity<List<Message>> response = controller.findBySender("Kate", null);
        HttpStatus actual = response.getStatusCode();
        Iterable<Message> actualMessages = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedMessages, actualMessages);
    }

    @Test
    public void testDelete() {
        //Given
        Message message1 = new Message("default", "Cara", new Date(), "testing");
        Boolean expected = true;
        HttpStatus expectedStatus = HttpStatus.NOT_FOUND;
        BDDMockito
                .given(service.delete(message1.getId()))
                .willReturn(expected);
        //When
        ResponseEntity<Boolean> response = controller.delete(message1.getId());
        HttpStatus actualStatus = response.getStatusCode();
        Boolean actual = response.getBody();

        //Then
        Assert.assertEquals(expectedStatus, actualStatus);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteAll() {
        //Given

        Boolean expected = true;
        HttpStatus expectedStatus = HttpStatus.NOT_FOUND;
        BDDMockito
                .given(service.deleteAll())
                .willReturn(expected);
        //When
        ResponseEntity<Boolean> response = controller.deleteAllMessages();
        HttpStatus actualStatus = response.getStatusCode();
        Boolean actual = response.getBody();

        //Then
        Assert.assertEquals(expectedStatus, actualStatus);
        Assert.assertEquals(expected, actual);

    }
}
