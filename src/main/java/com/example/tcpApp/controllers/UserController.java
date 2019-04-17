package com.example.tcpApp.controllers;

import com.example.tcpApp.models.User;
import com.example.tcpApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findusername/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/findByChannel/{channelId}")
    public ResponseEntity<List<User>> findByChannel(@PathVariable Long channelId, Pageable pageable){
        return new ResponseEntity<>(userService.findAllByChannels(channelId, pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}/connect")
    public ResponseEntity<User> connect(@PathVariable Long id){
        return new ResponseEntity<>(userService.connect(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/disconnect")
    public ResponseEntity<User> disconnect(@PathVariable Long id){
        return new ResponseEntity<>(userService.disconnect(id), HttpStatus.OK);
    }

    @PutMapping("/login/{username}")
    public ResponseEntity<User> login(@PathVariable String username){
        return new ResponseEntity<>(userService.login(username), HttpStatus.OK);
    }

    @PutMapping("/logout/{username}")
    public ResponseEntity<User> logout(@PathVariable String username){
        return new ResponseEntity<>(userService.logout(username), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.delete(id), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAllUsers(){
        return new ResponseEntity<>(userService.deleteAll(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/joinChannel")
    public ResponseEntity<User> joinChannel(@PathVariable Long id, @RequestParam Long channelId){
        return new ResponseEntity<>(userService.joinChannel(id, channelId), HttpStatus.OK);
    }

    @PutMapping("/{username}/join/")
    public ResponseEntity<User> joinChannel(@PathVariable String username, @RequestParam String channel){
        return new ResponseEntity<>(userService.joinChannelByName(username, channel), HttpStatus.OK);
    }

    @PutMapping("/{username}/leave/")
    public ResponseEntity<User> leaveChannel(@PathVariable String username, @RequestParam String channel){
        return new ResponseEntity<>(userService.leaveChannel(username, channel), HttpStatus.OK);
    }

}
