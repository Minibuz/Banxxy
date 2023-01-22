package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.jms.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class RedirectionController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH, method = {RequestMethod.GET, RequestMethod.POST})
    public String error() {
        return "forward:/";
    }

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping(value = "/api/jms", method = RequestMethod.GET)
    public void test() {
        jmsTemplate.convertAndSend("mailbox", new Log("Hello"));
    }
}
