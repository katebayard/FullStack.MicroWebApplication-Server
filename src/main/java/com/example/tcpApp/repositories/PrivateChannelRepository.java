package com.example.tcpApp.repositories;

import com.example.tcpApp.models.PrivateChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateChannelRepository extends JpaRepository<PrivateChannel, Long> {

    PrivateChannel findByChannelName(String channelName);
}
