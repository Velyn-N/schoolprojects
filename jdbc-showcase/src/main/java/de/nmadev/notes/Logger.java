package de.nmadev.notes;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private String topic;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");

    public Logger(String topic) {
        this.topic = topic;
    }

    public void log(String message) {
        System.out.println(topic + " [" + LocalTime.now().format(timeFormatter) + "] >> " + message);
    }

    public void log(Object object) {
        log(object.toString());
    }
}
