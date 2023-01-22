package fr.esipe.banxxy.configuration;

import fr.esipe.banxxy.dto.jms.Log;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Log log) {
        System.out.println("Received <" + log + ">");
    }
}
