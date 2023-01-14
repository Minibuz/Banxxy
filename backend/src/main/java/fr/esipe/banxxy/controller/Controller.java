package fr.esipe.banxxy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value = "{_:^(?!api).$}")
    public String redirectApi() {
        return "forward:/";
    }
}
