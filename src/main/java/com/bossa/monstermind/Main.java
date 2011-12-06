package com.bossa.monstermind;

import com.bossa.monstermind.messaging.Message;
import com.bossa.monstermind.messaging.MessagesListener;
import com.bossa.monstermind.logging.FileOutput;
import com.bossa.monstermind.logging.Messenger;

public class Main {

    public static void main(String[] args) throws Exception {
        MessagesListener listener = MessagesListener.getMessagesHandler(
                new Messenger(new FileOutput("messages.log")));

        Message message1 = new Message("Thomas", "I am going to destroy your city! Muhahahah",
                Message.Type.client_message);

        Message message2 = new Message("System", "Validaton error: Invalid monster",
                Message.Type.client_message);

        Message message3 = new Message("Oliver", "Oh noes! I can't believe you did that!",
                Message.Type.client_message);

        listener.processMessage(message1);
        listener.processMessage(message2);
        listener.processMessage(message3);

        listener.shutdown();
    }
}
