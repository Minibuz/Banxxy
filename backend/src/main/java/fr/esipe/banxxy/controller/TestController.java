package fr.esipe.banxxy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> all() {
        return new ResponseEntity<>("Shit work", HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADVISOR')")
    public String userAccess() {
        return "User Content.";
    }
}