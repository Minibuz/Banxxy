package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.user.UserDetailDto;
import fr.esipe.banxxy.dto.user.UserDto;
import fr.esipe.banxxy.dto.user.UserReceivedDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Get the list of users depending on the user received
     * @param userId the id of the user received
     * @return the list of children if the user is a customer, the list of customers if the user is an advisor, otherwise an empty Collection
     */
    List<UserDto> getDependantUser(Long userId);

    /**
     * Get an objet containing details of the user associated with the userId given
     * @param userId the id of the user
     * @return objet containing details of the user
     */
    UserDetailDto getUser(Long userId);

    /**
     * Create a customer based on datas received
     * @param userReceivedDto datas about de customer to create
     * @return an Optional containing the user if the creation succeed, otherwise empty
     */
    Optional<UserEntity> createCustomer(UserReceivedDto userReceivedDto);

    /**
     * Create a customer based on datas received
     * @param userReceivedDto datas about de customer to create
     * @return an Optional containing the user if the creation succeed, otherwise empty
     */
    Optional<UserEntity> createAdvisor(UserReceivedDto userReceivedDto);

    Boolean deleteUser(Long userId);
}
