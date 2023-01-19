package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.UserDetailDto;
import fr.esipe.banxxy.dto.UserDto;
import fr.esipe.banxxy.dto.UserReceivedDto;
import fr.esipe.banxxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    public ResponseEntity<List<UserDto>> getDependantUser(@PathVariable Long userId) {
        var users = userService.getDependantUser(userId);
        if(users.equals(Collections.emptyList()))
            return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PostMapping("/customer/create")
    public ResponseEntity<UserEntity> createCustomer(@RequestBody UserReceivedDto userReceivedDto) {
        return userService.createUser(userReceivedDto).map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailDto> getUser(@PathVariable Long userId) {
        var user = userService.getUser(userId);
        if(user == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
