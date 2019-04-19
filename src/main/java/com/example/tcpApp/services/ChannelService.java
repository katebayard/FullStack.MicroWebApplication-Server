package com.example.tcpApp.services;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {

    private ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository){
        this.channelRepository = channelRepository;
    }

    public Channel create(Channel channel){
        return channelRepository.save(channel);
    }

    public Channel findByChannelName(String name){
        return channelRepository.findByChannelName(name);
    }

    public Channel findById(Long id){
        return channelRepository.getOne(id);
    }

    public Iterable<Channel> findAll(){
        return channelRepository.findAll();
    }

    public Channel addUser(User user, Long channelId){
        Channel original = channelRepository.getOne(channelId);
        original.getUsers().add(user);
        return channelRepository.save(original);
    }

    public Channel removeUser(User user, String channel){
        Channel original = channelRepository.findByChannelName(channel);
        original.getUsers().remove(user);
        return channelRepository.save(original);
    }

    public Boolean delete(Long id){
        channelRepository.deleteById(id);
        return true;
    }
}
