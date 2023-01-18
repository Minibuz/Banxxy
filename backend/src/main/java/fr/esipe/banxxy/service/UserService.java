package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.UserDetailDto;
import fr.esipe.banxxy.dto.UserDto;
import fr.esipe.banxxy.dto.UserReceivedDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getDependantUser(Long userId);
    UserDetailDto getUser(Long userId);

    Optional<UserEntity> createUser(UserReceivedDto userReceivedDto);
}
