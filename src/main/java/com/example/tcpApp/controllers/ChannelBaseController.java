package com.example.tcpApp.controllers;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.services.ChannelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class ChannelBaseController<T extends Channel> {

    protected ChannelService<T> channelService;

    public ChannelBaseController(ChannelService<T> channelService) {
        this.channelService = channelService;
    }

    @PostMapping
    public ResponseEntity<T> createChannel(@RequestBody T channel) {
        return new ResponseEntity<>(channelService.create(channel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<T>> findAll(){
        return new ResponseEntity<>(channelService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable Long id){
        return new ResponseEntity<>(channelService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<T> findByName(@PathVariable String name){
        return new ResponseEntity<>(channelService.findByChannelName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return new ResponseEntity<>(channelService.delete(id), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/addUser")
    public ResponseEntity<T> addUser(@RequestBody User user, @PathVariable Long id){
        return new ResponseEntity<>(channelService.addUser(user, id), HttpStatus.OK);
    }

    @PutMapping("/{channel}/removeUser")
    public ResponseEntity<T> removeUser(@RequestBody User user, @PathVariable String channel){
        return new ResponseEntity<>(channelService.removeUser(user, channel), HttpStatus.OK);
    }



}


