package com.example.tcpApp.services;

import com.example.tcpApp.models.User;
import com.example.tcpApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User updatedUser) {
        User original = userRepository.getOne(id);
        original.setFirstName(updatedUser.getFirstName());
        original.setLastName(updatedUser.getLastName());
        original.setChannels(updatedUser.getChannels());
        original.setUsername(updatedUser.getUsername());
        original.setMessages(updatedUser.getMessages());
        userRepository.deleteById(id);
        return userRepository.save(original);
    }

    public Boolean delete(Long id) {
        userRepository.deleteById(id);
        return true;
    }
}
