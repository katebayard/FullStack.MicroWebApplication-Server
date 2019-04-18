package com.example.tcpApp;

import com.example.tcpApp.models.User;
import com.example.tcpApp.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    public void testFindByUsername() throws Exception {
        User user = new User();
        user.setFirstName("Maria");
        user.setLastName("Ortiz");
        user.setUsername("mariao");

        when(mockUserService.findByUsername("mariao")).thenReturn(user);

        mockMvc.perform(get("/users/findusername/{username}", "mariao"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.firstName", is("Maria")))
                .andExpect(jsonPath("$.lastName", is("Ortiz")))
                .andExpect(jsonPath("$.username", is("mariao")));

        verify(mockUserService, times(1)).findByUsername("mariao");
        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testFindChannel() throws Exception {
        User user1 = new User();
        user1.setFirstName("Amelia");
        user1.setLastName("DiMarco");
        user1.setUsername("adimarco");
        User user2 = new User();
        user2.setFirstName("James");
        user2.setLastName("Franco");
        user2.setUsername("frankie");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Pageable pageable = PageRequest.of(0, 20);

        when(mockUserService.findAllByChannels(1L, pageable)).thenReturn(users);

        mockMvc.perform(get("/users/findByChannel/{channelId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].firstName", is("Amelia")))
                .andExpect(jsonPath("$[0].lastName", is("DiMarco")))
                .andExpect(jsonPath("$[0].username", is("adimarco")))
                .andExpect(jsonPath("$[1].firstName", is("James")))
                .andExpect(jsonPath("$[1].lastName", is("Franco")))
                .andExpect(jsonPath("$[1].username", is("frankie")));

        verify(mockUserService, times(1)).findAllByChannels(1L, pageable);
        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testConnect() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Samantha");
        user.setLastName("Fuman");
        user.setUsername("sfuman");
        user.setConnected(true);

        when(mockUserService.connect(1L)).thenReturn(user);

        mockMvc.perform(put("/users/{id}/connect", 1L))
                .andExpect(status().isOk());

        verify(mockUserService, times(1)).connect(1L);
        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testDisconnect() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Tanya");
        user.setLastName("Wilkens");
        user.setUsername("twilkens");
        user.setConnected(false);

        when(mockUserService.disconnect(1L)).thenReturn(user);

        mockMvc.perform(put("/users/{id}/connect", 1L))
                .andExpect(status().isOk());

        verify(mockUserService, times(1)).connect(1L);
        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testLogin() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Rob");
        user.setLastName("Denney");
        user.setUsername("robdenney");
        user.setConnected(true);

        when(mockUserService.login("robdenney")).thenReturn(user);

        mockMvc.perform(put("/users//login/{username}", "robdenney")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(mockUserService, times(1)).login("robdenney");
        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testLogout() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Tara");
        user.setLastName("D'Ottavio");
        user.setUsername("tdottavio");
        user.setConnected(false);

        when(mockUserService.connect(1L)).thenReturn(user);

        mockMvc.perform(put("/users//logout/{username}", "tdottavio"))
                .andExpect(status().isOk());

        verify(mockUserService, times(1)).logout("tdottavio");
        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testDeleteUser() throws Exception {
        when(mockUserService.delete(1L)).thenReturn(true);

        mockMvc.perform(delete("/users//{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("true"));


        verify(mockUserService, times(1)).delete(1L);
        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testDeleteAllUsers() throws Exception {
        when(mockUserService.deleteAll()).thenReturn(true);

        mockMvc.perform(delete("/users/"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("true"));


        verify(mockUserService, times(1)).deleteAll();
        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testJoinChannel() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Abby");
        user.setLastName("Clark");
        user.setUsername("aclark");

        when(mockUserService.joinChannel(1L, 0L)).thenReturn(user);

        mockMvc.perform(put("/users/{id}/joinChannel", 1L)
                .param("channelId", "0"))
                .andExpect(status().isOk());

        verify(mockUserService, times(1)).joinChannel(1L, 0L);
        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testJoinChannelByName() throws Exception {
        User user = new User();
        user.setUsername("greta");

        when(mockUserService.joinChannelByName("greta", "mychannel")).thenReturn(user);

        mockMvc.perform(put("/users/{username}/join/", "greta")
                .param("channel", "mychannel"))
                .andExpect(status().isOk());

        verify(mockUserService, times(1)).joinChannelByName("greta", "mychannel");
        verifyNoMoreInteractions(mockUserService);
    }

}
