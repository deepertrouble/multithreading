package com.bossa.monstermind.messaging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MessageQueue {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageQueue.class);

    // Using a LinkedBlockingQueue for now. this implementation can be changed later
    private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<String>(100);

    public void put(final Message message)  throws InterruptedException {
        this.messageQueue.put(message.getFormattedData());
    }

    public String get() throws InterruptedException {
        return this.messageQueue.poll(1, TimeUnit.SECONDS);
    }
}
