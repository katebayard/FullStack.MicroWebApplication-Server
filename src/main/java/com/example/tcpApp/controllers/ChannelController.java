package com.example.tcpApp.controllers;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChannelController {

    private ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService){
        this.channelService = channelService;
    }

    @PostMapping("/channels/")
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channel){
        return new ResponseEntity<>(channelService.create(channel), HttpStatus.CREATED);
    }

    @GetMapping("/channels/{id}")
    public ResponseEntity<Channel> findById(@PathVariable Long id){
        return new ResponseEntity<>(channelService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/channels/")
    public ResponseEntity<List<Channel>> findAll(){
        return new ResponseEntity<>(channelService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/channels/{id}")
    public ResponseEntity<Channel> update(@PathVariable Long id, @RequestBody Channel channel){
        return new ResponseEntity<>(channelService.update(id, channel), HttpStatus.OK);
    }

    @DeleteMapping("/channels/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return new ResponseEntity<>(channelService.delete(id), HttpStatus.NOT_FOUND);
    }

    @GetMapping("channels/{id}/getUsers")
    public ResponseEntity<List<User>> getUsers(@PathVariable Long id){
        return new ResponseEntity<>(channelService.findAllUsers(id), HttpStatus.OK);
    }

    @PutMapping("channels/{id}/addUser")
    public ResponseEntity<Channel> addUser(@PathVariable Long id, @RequestParam Long userId) {
        return new ResponseEntity<>(channelService.addUser(id, userId), HttpStatus.OK);
    }

    @PutMapping("channels/{id}/removeUser")
    public ResponseEntity<Channel> removeUser(@PathVariable Long id, @RequestParam Long userId){
        return new ResponseEntity<>(channelService.removeUser(id, userId), HttpStatus.OK);
    }
}
