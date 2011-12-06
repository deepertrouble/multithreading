package com.bossa.monstermind.messaging;


import java.util.Date;

public class Message {

    private final String sender;
    private final String text;
    private final Type type;

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public Message(String sender, String text, Type messageType) {
        this.sender = sender;
        this.text = text;
        this.type = messageType;
    }

    public Type getType() {
        return type;
    }

    // format is "Sender: Text @ Time"
    public String getFormattedData() {
        return new StringBuilder().append(this.getSender())
                .append(" :  ").append(this.getText()).append(" @ ").append(new Date()).toString();
    }

    // only dealing with game messages at the moment
    public enum Type {
        client_message;
    }
}
