package com.example.tcpApp.services;

import com.example.tcpApp.models.Message;

import java.util.List;

public interface  MessageService {
    List<Message> findAll();
    Message findById(Long id);
    Message create(Message message);
    Message edit(Message message);
    void delete(Long id);
}
