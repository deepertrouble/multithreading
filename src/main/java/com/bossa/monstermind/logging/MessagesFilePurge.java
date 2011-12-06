package com.bossa.monstermind.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

class MessagesFilePurge extends TimerTask {
        private static final Logger LOGGER = LoggerFactory.getLogger(MessagesFilePurge.class);

        private final String fileName;
        // this is used only for the naming of the file. eg: messages.log.1, messages.log.2
        private static int fileCounter = 0;

        public MessagesFilePurge (final String fileName) {
            this.fileName = fileName;

        }

        @Override
        public void run() {
            File logFile = new File(fileName);
            String purgedFileName = fileName + "." +  (++fileCounter);

            LOGGER.debug("Creating new file name : {} " , purgedFileName);

            File purgedFile = new File(purgedFileName);
            logFile.renameTo(purgedFile);
            purgedFile = null;

            try {
                 logFile.createNewFile();
            }  catch (IOException ioEx) {
                  LOGGER.error("Error creating log");
            }
        }
}
