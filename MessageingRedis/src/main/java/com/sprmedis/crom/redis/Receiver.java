package com.sprmedis.crom.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息接收器
 * 接收方正在对收到的消息进行计数。这样，它可以在收到消息时发出信号
 */
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    private AtomicInteger counter = new AtomicInteger();
    public void receiveMessage(String message){
        LOGGER.info("Received<" + message + ">");
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }
}
