package com.example.tcpApp.controllers;

import com.example.tcpApp.models.PrivateChannel;
import com.example.tcpApp.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/privatechannels")
public class PrivateChannelController extends ChannelBaseController<PrivateChannel> {
    
    @Autowired
    public PrivateChannelController(ChannelService<PrivateChannel> channelService) {
        super(channelService);
    }
}


