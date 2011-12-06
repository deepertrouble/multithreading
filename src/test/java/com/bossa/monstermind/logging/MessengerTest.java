package com.bossa.monstermind.logging;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(Parameterized.class)
public class MessengerTest {

    private String data;
    private boolean expectedResult;
    private String fileName = "test.log"  ;

    public MessengerTest(String data, boolean expectedResult) {
        this.data = data;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateData()  {
         return Arrays.asList(new Object[][]{
                 {"hello", true},
                 {"why", true},
                 {"what", true},
                 {"dump", true},
                 {"seeyou", true},
                 {"lost", true},
         });
    }

    @Test
    public void whenValidMessages() {
        Messenger messenger = new Messenger(new FileOutput(fileName));
        boolean actualResult = messenger.recordMessage(this.data);
        assertThat(actualResult, is(this.expectedResult));
    }

    @After
    public void after() {
        File file = new File(fileName);
        file.deleteOnExit();
    }
}
