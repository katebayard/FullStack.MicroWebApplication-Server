package com.example.tcpApp.repositories;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    Channel findByChannelName(String channelName);
}
