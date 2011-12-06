package com.bossa.monstermind.messaging;

import com.bossa.monstermind.logging.Messenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This is the class that can be used on a port . Started as a daemon
 */
public class MessagesListener extends Thread implements Listener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessagesListener.class);

    // for adding messages to queue
    private final ExecutorService producerService;
    // for removing message from queue and writing them to a file
    private final ExecutorService consumerService;
    // queue for the messages
    private final MessageQueue messageQueue;
    // Messenger that records the messages
    private final Messenger messenger;
    //
    private boolean threadPoolTerminated = false;

    private static MessagesListener instance;

    public static MessagesListener getMessagesHandler(final Messenger messenger) {
        if(instance == null) {
            instance = new MessagesListener(messenger);
        }
        return instance;
    }

    private MessagesListener(final Messenger messenger) {
          producerService = Executors.newCachedThreadPool();
          consumerService = Executors.newFixedThreadPool(2);
          messageQueue = new MessageQueue();
          this.messenger = messenger;
          this.setDaemon(true);
          this.start();
    }

    public void processMessage(final Message message) {
         producerService.execute(new MessageProducer(messageQueue, message));
    }

    public void run() {
        while(!threadPoolTerminated) {
            consumerService.execute(new MessageConsumer(messageQueue, messenger));
        }
    }

    public void shutdown() throws InterruptedException {
        producerService.shutdown();
        threadPoolTerminated = true;
        producerService.awaitTermination(10, TimeUnit.SECONDS);
        consumerService.shutdown();
        System.exit(0);
    }

}
