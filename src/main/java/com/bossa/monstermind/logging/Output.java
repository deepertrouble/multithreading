package com.bossa.monstermind.logging;


import com.bossa.monstermind.exceptions.FileWriteException;

public interface Output {
    void write(String data) throws FileWriteException;
}
