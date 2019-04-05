package com.example.tcpApp.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "channel")
    List<Message> messages = new ArrayList<>();
    @ManyToMany(mappedBy = "channels")
    List<User> users = new ArrayList<>();

    public Channel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", messages=" + messages +
              //  ", users=" + users +
                '}';
    }
}
