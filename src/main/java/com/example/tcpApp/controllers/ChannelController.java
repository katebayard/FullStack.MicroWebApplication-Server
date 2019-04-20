package com.example.tcpApp.controllers;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/channels")
public class ChannelController {

    @Autowired
    private Channel defaultChannel;

    private ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
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

    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channel) {
        return new ResponseEntity<>(channelService.create(channel), HttpStatus.CREATED);
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
}


