package fr.esipe.banxxy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> all() {
        return new ResponseEntity<>("Shit work", HttpStatus.OK);
    }
}