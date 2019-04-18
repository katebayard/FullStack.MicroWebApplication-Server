package com.example.tcpApp.controllerTests;


import com.example.tcpApp.models.Message;
import com.example.tcpApp.repositories.MessageRespository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MessageControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MessageRespository repository;

    @Test
    public void testSendMessage() throws Exception{
        //Given
        Message expectedMessage = new Message("default", "Cara", new Date(2019, 03, 17, 9, 12, 30), "testing");
        BDDMockito
                .given(repository.save(expectedMessage))
                .willReturn(expectedMessage);
        String messageAsJson = "{\"Message\":{\"id\":null,\"channel\":\"default\",\"sender\":\"Cara\",\"timestamp\":\"3919-04-17T13:12:30.000+0000\",\"messageContent\":\"testing\"}}";

        String expectedContent = "[{\"id\":null,\"channel\":\"default\",\"sender\":\"Cara\",\"timestamp\":\"3919-04-17T13:12:30.000+0000\",\"messageContent\":\"testing\"}]";
        this.mvc.perform(MockMvcRequestBuilders
            .post("/messages")
            .content(messageAsJson)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testFindAll() throws Exception{
        //Given
        Message message1 = new Message("default", "Cara", new Date(2019, 03, 17, 9, 12, 30), "testing");
        Message message2 = new Message("default", "Cara", new Date(2019, 03, 17, 9, 14, 20), "testing1");
        Message message3 = new Message("default", "Kate", new Date(2019, 03, 17, 9, 15, 15), "testing2");

        List<Message> expectedMessages = new ArrayList<>();
        expectedMessages.add(message1);
        expectedMessages.add(message2);
        expectedMessages.add(message3);

        BDDMockito
                .given(repository.findAll())
                .willReturn(expectedMessages);

        String expectedContent = "[{\"id\":null,\"channel\":\"default\",\"sender\":\"Cara\",\"timestamp\":\"3919-04-17T13:12:30.000+0000\",\"messageContent\":\"testing\"}," +
                "{\"id\":null,\"channel\":\"default\",\"sender\":\"Cara\",\"timestamp\":\"3919-04-17T13:14:20.000+0000\",\"messageContent\":\"testing1\"}," +
                "{\"id\":null,\"channel\":\"default\",\"sender\":\"Kate\",\"timestamp\":\"3919-04-17T13:15:15.000+0000\",\"messageContent\":\"testing2\"}]";

        this.mvc.perform(MockMvcRequestBuilders
                .get("/messages")
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void testFindAllByChannel() throws Exception{
        //Given
        Message message1 = new Message("default", "Cara", new Date(2019, 03, 17, 9, 12, 30), "testing");
        Message message2 = new Message("default", "Cara", new Date(2019, 03, 17, 9, 14, 20), "testing1");
        Pageable pageable = PageRequest.of(0, 20);

        List<Message> expectedMessages = new ArrayList<>();
        expectedMessages.add(message1);
        expectedMessages.add(message2);

        BDDMockito
                .given(repository.findAllByChannel("default", pageable))
                .willReturn(expectedMessages);
        String expectedContent = "[{\"id\":null,\"channel\":\"default\",\"sender\":\"Cara\",\"timestamp\":\"3919-04-17T13:12:30.000+0000\",\"messageContent\":\"testing\"}," +
                "{\"id\":null,\"channel\":\"default\",\"sender\":\"Cara\",\"timestamp\":\"3919-04-17T13:14:20.000+0000\",\"messageContent\":\"testing1\"}]";
        this.mvc.perform(MockMvcRequestBuilders
                .get("/messages/findAll/{channel}", "default")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void testFindBySender() throws Exception{
        //Given
        Message message1 = new Message("default", "Cara", new Date(2019, 03, 17, 9, 12, 30), "testing");
        Message message2 = new Message("default", "Cara", new Date(2019, 03, 17, 9, 14, 20), "testing1");
        Pageable pageable = PageRequest.of(0, 20);

        List<Message> expectedMessages = new ArrayList<>();
        expectedMessages.add(message1);
        expectedMessages.add(message2);

        BDDMockito
                .given(repository.findBySender("Cara", pageable))
                .willReturn(expectedMessages);
        String expectedContent = "[{\"id\":null,\"channel\":\"default\",\"sender\":\"Cara\",\"timestamp\":\"3919-04-17T13:12:30.000+0000\",\"messageContent\":\"testing\"}," +
                "{\"id\":null,\"channel\":\"default\",\"sender\":\"Cara\",\"timestamp\":\"3919-04-17T13:14:20.000+0000\",\"messageContent\":\"testing1\"}]";
        this.mvc.perform(MockMvcRequestBuilders
                .get("/messages/findBySender/{username}", "Cara")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void testDelete() throws Exception {
        //Given
        Message message1 = new Message("default", "Cara", new Date(2019, 03, 17, 9, 12, 30), "testing");
        Long testId = 101L;
        message1.setId(testId);

        this.mvc.perform(MockMvcRequestBuilders
            .delete("/messages/{id}", testId)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteAll() throws Exception{//Given
        Message message1 = new Message("default", "Cara", new Date(2019, 03, 17, 9, 12, 30), "testing");
        Long testId = 101L;
        message1.setId(testId);

        this.mvc.perform(MockMvcRequestBuilders
                .delete("/messages")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSend() {

    }
}
