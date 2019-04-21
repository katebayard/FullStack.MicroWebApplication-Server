package com.example.tcpApp.configurations;

import com.example.tcpApp.models.Channel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelConfig {

    @Bean
    public Channel defaultChannel() {
        return new Channel("Main Channel");
    }
}
