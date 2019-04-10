package com.example.tcpApp.services;

import com.example.tcpApp.models.User;
import com.example.tcpApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public Boolean delete(Long id) {
        userRepository.deleteById(id);
        return true;
    }
}
