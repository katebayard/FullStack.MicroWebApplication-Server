package com.example.tcpApp.controllers;

import com.example.tcpApp.models.PrivateChannel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.services.PrivateChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/privatechannels")
public class PrivateChannelController {

    private PrivateChannelService privateChannelService;

    @Autowired
    public PrivateChannelController(PrivateChannelService privateChannelService) {
        this.privateChannelService = privateChannelService;
    }

    @PostMapping
    public ResponseEntity<PrivateChannel> createPrivateChannel(@RequestBody PrivateChannel channel) {
        return new ResponseEntity<>(privateChannelService.create(channel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<PrivateChannel>> findAll(){
        return new ResponseEntity<>(privateChannelService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrivateChannel> findById(@PathVariable Long id){
        return new ResponseEntity<>(privateChannelService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<PrivateChannel> findByName(@PathVariable String name){
        return new ResponseEntity<>(privateChannelService.findByPrivateChannelName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return new ResponseEntity<>(privateChannelService.delete(id), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/addUser")
    public ResponseEntity<PrivateChannel> addUser(@RequestBody User user, @PathVariable Long id){
        return new ResponseEntity<>(privateChannelService.addUser(user, id), HttpStatus.OK);
    }

    @PutMapping("/{channel}/removeUser")
    public ResponseEntity<PrivateChannel> removeUser(@RequestBody User user, @PathVariable String channel){
        return new ResponseEntity<>(privateChannelService.removeUser(user, channel), HttpStatus.OK);
    }

}


