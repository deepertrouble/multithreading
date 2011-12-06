package com.bossa.monstermind.logging;


import com.bossa.monstermind.exceptions.FileWriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * records the messages
 */
public class Messenger {
    private static final Logger LOGGER = LoggerFactory.getLogger(Messenger.class);
    private final Output output;

    public Messenger(final Output output) {
        this.output = output;
    }

    public boolean  recordMessage(final String message) {
        LOGGER.debug("Recording message {} ", message);
        try {
            output.write(message);
            return true;
        } catch (FileWriteException fileEx) {
            return false;
        }
    }

}
