package com.example.tcpApp.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("private message")
public class PrivateChannel extends BaseChannel {
}
