package com.bossa.monstermind.logging;


import com.bossa.monstermind.exceptions.FileWriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;

public class FileOutput implements Output {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileOutput.class);
    private static int LOG_FILE_ROLLING_TIMEOUT = 5 * 60 * 1000;
    private Timer timer;

    private final String fileName;

    public FileOutput(final String fileName) {
        this.fileName = fileName;
        timer = new Timer(true);
        timer.schedule(new MessagesFilePurge(fileName), LOG_FILE_ROLLING_TIMEOUT, LOG_FILE_ROLLING_TIMEOUT );
    }

    /**
     * this could be improved by writing more than one message at once
     * @param data
     */
    @Override
    public void write(final String data) throws FileWriteException {
        LOGGER.debug("Writing message into file:  {} ", data);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(data);
            out.newLine();
            out.close();
        } catch (IOException e) {
            LOGGER.error("Error writing into file for message {} ", data);
            throw new FileWriteException(e);
        }
    }

}
