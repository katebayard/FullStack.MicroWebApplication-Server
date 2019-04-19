package com.example.tcpApp.services;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.repositories.ChannelBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService<T extends Channel> {

    private ChannelBaseRepository<T> channelRepository;

    @Autowired
    public ChannelService(ChannelBaseRepository<T> channelRepository) {
        this.channelRepository = channelRepository;
    }

    public T create(T channel){
        return channelRepository.save(channel);
    }

    public T findByChannelName(String name){
        return channelRepository.findByChannelName(name);
    }

    public T findById(Long id){
        return channelRepository.getOne(id);
    }

    public Iterable<T> findAll(){
        return channelRepository.findAll();
    }

    public T addUser(User user, Long channelId){
        T original = channelRepository.getOne(channelId);
        original.getUsers().add(user);
        return channelRepository.save(original);
    }

    public T removeUser(User user, String channel){
        T original = channelRepository.findByChannelName(channel);
        original.getUsers().remove(user);
        return channelRepository.save(original);
    }

    public Boolean delete(Long id){
        channelRepository.deleteById(id);
        return true;
    }
}
