package com.bossa.monstermind.messaging;


import com.bossa.monstermind.logging.Messenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageConsumer implements Runnable{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    private final MessageQueue messageQueue;
    private final Messenger messenger;

    public MessageConsumer(MessageQueue messageQueue, Messenger messenger) {
        this.messageQueue = messageQueue;
        this.messenger = messenger;
    }

    @Override
    public void run() {
        try {
            String data;
            while((data = messageQueue.get()) != null) {
                  LOGGER.debug("Removing message from queue : ");
//                  Thread.sleep(1000);
                  messenger.recordMessage(data);
            }
        }   catch (InterruptedException iEx) {
             LOGGER.error("Interruption while removing data from message queue");
        }
    }
}
