package com.example.tcpApp.services;

import com.example.tcpApp.models.Message;
import com.example.tcpApp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class MessageServiceJpaImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message findById(Long id) {
        return messageRepository.getOne(id);
    }

    @Override
    public Message create(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message edit(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }
}
