package com.example.tcpApp.controllers;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;


@RestController
@RequestMapping("/channels")
public class ChannelController {

    private ChannelService channelService;

    @Autowired
    private Channel defaultChannel;

    @Autowired
    public ChannelController(ChannelService channelService){
        this.channelService = channelService;
    }

    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channel) {
        return new ResponseEntity<>(channelService.create(channel), HttpStatus.CREATED);
    }

    @PostMapping("/default")
    public ResponseEntity<Channel> createDefaultChannel() {
        for (Channel channel: findAll().getBody()) {
            if (channel.getChannelName().equals("Main Channel")) {
                return new ResponseEntity<>(channel, HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(channelService.create(defaultChannel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Channel>> findAll(){
        return new ResponseEntity<>(channelService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Channel> findById(@PathVariable Long id){
        return new ResponseEntity<>(channelService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<Channel> findByName(@PathVariable String name){
        return new ResponseEntity<>(channelService.findByChannelName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return new ResponseEntity<>(channelService.delete(id), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/addUser")
    public ResponseEntity<Channel> addUser(@RequestBody User user, @PathVariable Long id){
        return new ResponseEntity<>(channelService.addUser(user, id), HttpStatus.OK);
    }

    @PutMapping("/{channel}/removeUser")
    public ResponseEntity<Channel> removeUser(@RequestBody User user, @PathVariable String channel){
        return new ResponseEntity<>(channelService.removeUser(user, channel), HttpStatus.OK);
    }


}


