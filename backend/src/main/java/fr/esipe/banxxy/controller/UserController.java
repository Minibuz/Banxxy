package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.UserDto;
import fr.esipe.banxxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public List<UserDto> getDependantUser(@PathVariable Long userId) {
        return userService.getDependantUser(userId);
    };
}
