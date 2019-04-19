package com.example.tcpApp.repositories;

import com.example.tcpApp.models.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelBaseRepository<T extends Channel> extends JpaRepository<T, Long> {

    T findByChannelName(String channelName);
}
