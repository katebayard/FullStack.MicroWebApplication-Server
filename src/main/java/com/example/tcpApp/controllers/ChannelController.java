package com.example.tcpApp.controllers;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/channels")
public class ChannelController extends ChannelBaseController<Channel> {

    @Autowired
    private Channel defaultChannel;

    @Autowired
    public ChannelController(ChannelService<Channel> channelService) {
        super(channelService);
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

}


