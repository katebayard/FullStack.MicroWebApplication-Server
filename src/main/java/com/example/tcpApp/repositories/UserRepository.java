package com.example.tcpApp.repositories;

import com.example.tcpApp.models.Channel;
import com.example.tcpApp.models.Message;
import com.example.tcpApp.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    List<User> findAllByChannels(Channel channel, Pageable pageable);

}
