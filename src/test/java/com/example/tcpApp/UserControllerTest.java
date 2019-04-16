package com.example.tcpApp;

import com.example.tcpApp.models.User;
import com.example.tcpApp.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setFirstName("Tony");
        user.setLastName("Hughes");
        user.setUsername("tonyhughes");
        when(mockUserService.create(user)).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\n" +
                        "    \"firstName\": \"Tony\",\n" +
                        "    \"lastName\": \"Hughes\",\n" +
                        "    \"username\": \"tonyhughes\"\n" +
                        "}"))
                .andExpect(status().isCreated());
        verify(mockUserService, times(1)).create(refEq(user));
        verifyNoMoreInteractions(mockUserService);
        System.out.println("ran");
    }

    @Test
    public void testFindById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Cara");
        user.setLastName("Eppes");
        user.setUsername("caraeppes");

        when(mockUserService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Cara")))
                .andExpect(jsonPath("$.lastName", is("Eppes")))
                .andExpect(jsonPath("$.username", is("caraeppes")));

        verify(mockUserService, times(1)).findById(1L);
        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testFindAll() throws Exception {
        User user1 = new User();
        user1.setFirstName("Ashley");
        user1.setLastName("Bloxom");
        user1.setUsername("ashblox");
        User user2 = new User();
        user2.setFirstName("Kate");
        user2.setLastName("Bayard");
        user2.setUsername("kbayard");

        when(mockUserService.findAll()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("Ashley")))
                .andExpect(jsonPath("$[0].lastName", is("Bloxom")))
                .andExpect(jsonPath("$[0].username", is("ashblox")))
                .andExpect(jsonPath("$[1].firstName", is("Kate")))
                .andExpect(jsonPath("$[1].lastName", is("Bayard")))
                .andExpect(jsonPath("$[1].username", is("kbayard")));

        verify(mockUserService, times(1)).findAll();
        verifyNoMoreInteractions(mockUserService);
    }


}
