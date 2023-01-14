package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class TestController {

    private final TestService service;

    @Autowired
    TestController(TestService service) {
        this.service = service;
    }

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String all() {
        return "Shit work";
    }
}