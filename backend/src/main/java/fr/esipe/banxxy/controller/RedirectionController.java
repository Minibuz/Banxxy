package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.jms.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.ZonedDateTime;

@org.springframework.stereotype.Controller
public class RedirectionController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH, method = {RequestMethod.GET, RequestMethod.POST})
    public String error() {
        return "forward:/";
    }


    // This part is just an example, we won't use it like that in the near future.
    // This will be used for the command of the chequier made either by a customer
    // or an advisor.
    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping(value = "/api/jms/{authorId}/{accountId}", method = RequestMethod.GET)
    public void jmsSender(@PathVariable Long accountId,
                          @PathVariable Long authorId) {
        jmsTemplate.convertAndSend("mailbox", new Log(ZonedDateTime.now(), accountId, authorId));
    }
}
