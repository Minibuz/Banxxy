package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.CustomerEntity;
import fr.esipe.banxxy.dto.UserDto;
import fr.esipe.banxxy.repository.AdvisorRepository;
import fr.esipe.banxxy.repository.CustomerRepository;
import fr.esipe.banxxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicesImpl implements UserService {

    private final AdvisorRepository advisorRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public UserServicesImpl(AdvisorRepository advisorRepository, CustomerRepository customerRepository) {
        this.advisorRepository = advisorRepository;
        this.customerRepository = customerRepository;
    }

    private AdvisorEntity isAdvisor(Long userId) {
        return advisorRepository.findById(userId).orElse(null);
    }

    private CustomerEntity isCustomer(Long userId) {
        return customerRepository.findById(userId).orElse(null);
    }

    @Override
    public List<UserDto> getDependantUser(Long userId) {
        var advisor = isAdvisor(userId);
        var customer = isCustomer(userId);
        List<UserDto> userDtoList = new ArrayList<>();

        if (advisor != null) {
            var customers = advisor.getCustomers();
            customers.forEach(customerEntity -> {
                    userDtoList.add(new UserDto(
                            customerEntity.getFirstname(),
                            customerEntity.getName(),
                            customerEntity.getId()));
            });
            return userDtoList;
        } else if (customer != null) {
            var children = customer.getChildrens();
            children.forEach(customerEntity -> {
                userDtoList.add(new UserDto(
                        customerEntity.getFirstname(),
                        customerEntity.getName(),
                        customerEntity.getId()));
            });
            return userDtoList;
        }
        throw new IllegalArgumentException();
    }
}
