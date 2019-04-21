package com.example.tcpApp.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("channel")
public class Channel extends BaseChannel {

    public Channel() {
    }

    public Channel(String channelName) {
        super(channelName);
    }
}
