package com.example.tcpApp.repositories;

import com.example.tcpApp.models.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRespository extends JpaRepository<Message, Long> {

    List<Message> findAllByChannel(String channel, Pageable pageable);
    List<Message> findBySender(String sender, Pageable pageable);

}
