package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.UserDetailDto;
import fr.esipe.banxxy.dto.UserDto;
import fr.esipe.banxxy.dto.UserReceivedDto;

import java.util.List;

public interface UserService {
    List<UserDto> getDependantUser(Long userId);
    UserDetailDto getUser(Long userId);

    void createUser(UserReceivedDto userReceivedDto);
}
