package com.bossa.monstermind.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProducer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    private final MessageQueue messageQueue;
    private final Message message;

    public MessageProducer(final MessageQueue messageQueue, final Message message) {
        this.messageQueue = messageQueue;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            LOGGER.debug("Adding message into queue : {} " , message.getText());
            Thread.sleep(100);
            messageQueue.put(message);
        } catch (InterruptedException iEx) {
            LOGGER.error("Interruption while adding data into message queue");
        }

    }
}
