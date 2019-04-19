package com.example.tcpApp.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Private Message")
@DiscriminatorValue("private message")
public class PrivateChannel extends Channel {
}
