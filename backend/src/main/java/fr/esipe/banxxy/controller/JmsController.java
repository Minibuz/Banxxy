package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.jms.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class JmsController {

    private final JmsTemplate jmsTemplate;

    @Autowired
    JmsController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @RequestMapping(value = "/api/jms/{authorId}/{accountId}", method = RequestMethod.GET)
    public void jmsSender(@PathVariable Long accountId,
                          @PathVariable Long authorId) {
        jmsTemplate.convertAndSend("mailbox", new Log(ZonedDateTime.now(), accountId, authorId));
    }
}
