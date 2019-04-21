package com.example.tcpApp.modelTests;

import com.example.tcpApp.models.BaseChannel;
import com.example.tcpApp.models.PrivateChannel;
import org.junit.Assert;
import org.junit.Test;

public class PrivateChannelTests {

    @Test
    public void testInstanceOf() {
        Assert.assertTrue(new PrivateChannel() instanceof BaseChannel);
    }
}
