package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getDependantUser(Long userId);
}
