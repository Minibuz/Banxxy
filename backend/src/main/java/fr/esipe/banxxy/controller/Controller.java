package fr.esipe.banxxy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value = "{_:^(?!api).$}", method = {RequestMethod.GET, RequestMethod.POST})
    public String redirectApi() {
        return "forward:/";
    }
}
