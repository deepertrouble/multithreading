package com.bossa.monstermind.messaging;


public interface Listener {
    void processMessage(Message message);
}
