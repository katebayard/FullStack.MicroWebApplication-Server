package com.example.tcpApp.services;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.User;
import com.example.tcpApp.repositories.ChannelRepository;
import com.example.tcpApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Channel> findAll(){
        return channelRepository.findAll();
    }

    public Channel findById(Long id){
        return channelRepository.getOne(id);
    }

    public Channel create(Channel channel){
        return channelRepository.save(channel);
    }

    public Channel update(Long id, Channel channel){
        Channel original = channelRepository.getOne(id);
        original.setName(channel.getName());
        original.setMessages(channel.getMessages());
        original.setUsers(channel.getUsers());
       // channelRepository.deleteById(id);
        return channelRepository.save(original);
    }

    public Boolean delete(Long id){
        channelRepository.deleteById(id);
        return true;
    }

    public List<User> findAllUsers(Long id){
        List<User> allUsers = new ArrayList<>();
        for(User user : userRepository.findAll()){
            for(Channel channel : user.getChannels()){
                if(channel.getId().equals(id)){
                    allUsers.add(user);
                }
            }
        }
        return allUsers;
    }

    public Channel addUser(Long id, Long userId){
        Channel original = channelRepository.getOne(id);
        original.getUsers().add(userRepository.getOne(userId));
        userRepository.getOne(userId).getChannels().add(original);
        return channelRepository.save(original);
    }

    public Channel removeUser(Long id, Long userId){
        Channel original = channelRepository.getOne(id);
        original.getUsers().remove(userRepository.getOne(userId));
        userRepository.getOne(userId).getChannels().remove(channelRepository.getOne(id));
        return channelRepository.save(original);
    }

}