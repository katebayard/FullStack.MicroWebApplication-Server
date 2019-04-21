package com.example.tcpApp.services;

import com.example.tcpApp.models.PrivateChannel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.repositories.PrivateChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateChannelService {

    private PrivateChannelRepository privateChannelRepository;

    @Autowired
    public PrivateChannelService(PrivateChannelRepository privateChannelRepository) {
        this.privateChannelRepository = privateChannelRepository;
    }

    public PrivateChannel create(PrivateChannel channel){
        return privateChannelRepository.save(channel);
    }

    public PrivateChannel findByPrivateChannelName(String name){
        return privateChannelRepository.findByChannelName(name);
    }

    public PrivateChannel findById(Long id){
        return privateChannelRepository.getOne(id);
    }

    public Iterable<PrivateChannel> findAll(){
        return privateChannelRepository.findAll();
    }

    public PrivateChannel addUser(User user, Long channelId){
        PrivateChannel original = privateChannelRepository.getOne(channelId);
        original.getUsers().add(user);
        return privateChannelRepository.save(original);
    }

    public PrivateChannel removeUser(User user, String channel){
        PrivateChannel original = privateChannelRepository.findByChannelName(channel);
        original.getUsers().remove(user);
        return privateChannelRepository.save(original);
    }

    public Boolean delete(Long id){
        privateChannelRepository.deleteById(id);
        return true;
    }
}
