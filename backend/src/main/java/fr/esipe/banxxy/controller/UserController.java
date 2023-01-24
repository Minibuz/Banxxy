package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.user.UserDetailDto;
import fr.esipe.banxxy.dto.user.UserDto;
import fr.esipe.banxxy.dto.user.UserReceivedDto;
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

    /**
     * Return a ResponseEntity containing the list of users depending on the user received and the status of the response
     *
     * @param userId the id of the user received
     * @return the list of users and OK if the user sent exists, otherwise an empty Collection and NOT_FOUND
     */
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<UserDto>> getDependantUser(@PathVariable Long userId) {
        var users = userService.getDependantUser(userId);
        if (users.equals(Collections.emptyList()))
            return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    /**
     * Create a user using the datas received
     *
     * @param userReceivedDto an object containing datas to create the user
     * @return The user created and OK if the creation worked, otherwise null and UNAUTHORIZED
     */
    @PostMapping(value = "/customer/create",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<UserEntity> createCustomer(@RequestBody UserReceivedDto userReceivedDto) {
        return userService.createCustomer(userReceivedDto).map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED));
    }

    @PostMapping(value = "/advisor/create",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<UserEntity> createAdvisor(@RequestBody UserReceivedDto userReceivedDto) {
        return userService.createAdvisor(userReceivedDto).map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED));
    }

    /**
     * Return the details of the user received
     *
     * @param userId the id of the user to return details
     * @return Details of the user and OK if the user exists, otherwise null and NOT_FOUND
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailDto> getUser(@PathVariable Long userId) {
        var user = userService.getUser(userId);
        if (user == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
