package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.UserDetailDto;
import fr.esipe.banxxy.dto.UserDto;
import fr.esipe.banxxy.dto.UserReceivedDto;
import fr.esipe.banxxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all/{userId}")
    public List<UserDto> getDependantUser(@PathVariable Long userId) {
        return userService.getDependantUser(userId);
    };


    @PostMapping("/customer/create")
    public void createCustomer(@RequestBody UserReceivedDto userReceivedDto) {
        userService.createUser(userReceivedDto);
    }

    @GetMapping("/{userId")
    public UserDetailDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}
