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
    public ResponseEntity<UserEntity> createCustomer(@RequestBody UserReceivedDto userReceivedDto) {
        var created = userService.createUser(userReceivedDto);
        return created.map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED));
    }

    @GetMapping("/{userId")
    public UserDetailDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}
