package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.CustomerEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.UserDetailDto;
import fr.esipe.banxxy.dto.UserDto;
import fr.esipe.banxxy.dto.UserReceivedDto;
import fr.esipe.banxxy.repository.AdvisorRepository;
import fr.esipe.banxxy.repository.CustomerRepository;
import fr.esipe.banxxy.repository.UserRepository;
import fr.esipe.banxxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicesImpl implements UserService {

    private final AdvisorRepository advisorRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserServicesImpl(AdvisorRepository advisorRepository, CustomerRepository customerRepository, UserRepository userRepository) {
        this.advisorRepository = advisorRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    private AdvisorEntity getAdvisor(Long userId) {
        return advisorRepository.findById(userId).orElse(null);
    }

    private CustomerEntity getCustomer(Long userId) {
        return customerRepository.findById(userId).orElse(null);
    }

    private List<UserDto> getCustomerList(AdvisorEntity advisor) {
        List<UserDto> customerDtoList = new ArrayList<>();
        var customers = advisor.getCustomers();
        customers.forEach(customerEntity -> {
            customerDtoList.add(new UserDto(
                    customerEntity.getFirstname(),
                    customerEntity.getName(),
                    customerEntity.getId()));
        });
        return customerDtoList;
    }

    private List<UserDto> getChildrenList(CustomerEntity customer) {
        List<UserDto> childrenDtoList = new ArrayList<>();
        var children = customer.getChildrens();
        children.forEach(customerEntity -> {
            childrenDtoList.add(new UserDto(
                    customerEntity.getFirstname(),
                    customerEntity.getName(),
                    customerEntity.getId()));
        });
        return childrenDtoList;
    }
    @Override
    public List<UserDto> getDependantUser(Long userId) {
        var advisor = getAdvisor(userId);
        var customer = getCustomer(userId);

        if (advisor != null) {
            return getCustomerList(advisor);
        } else if (customer != null) {
            return getChildrenList(customer);
        }
        throw new IllegalArgumentException();
    }

    private UserDetailDto getUser(UserEntity user) {
        return new UserDetailDto(
                user.getFirstname(),
                user.getName(),
                user.getUsername(),
                user.getId(),
                user.getPassword(),
                user.getMail()
        );
    }

    @Override
    public UserDetailDto getUser(Long userId) {
        var advisor = getAdvisor(userId);
        if(advisor != null) {
            return getUser(advisor);
        }
        var customer = getCustomer(userId);
        if (customer != null){
            return getUser(customer);
        }
        throw new IllegalArgumentException();

    }

    @Override
    public void createUser(UserReceivedDto userReceivedDto) {
        UserEntity user = new UserEntity();
        user.setFirstname(userReceivedDto.getFirstName());
        user.setName(userReceivedDto.getLastName());
        user.setUsername(userReceivedDto.getUserName());
        user.setPassword(userReceivedDto.getPassword());
        user.setMail(userReceivedDto.getMail());
        user.setType("customer");
        userRepository.save(user);
        CustomerEntity customer = new CustomerEntity();
        customer.setAdvisor(getAdvisor(userReceivedDto.getAdvisorId()));
        customerRepository.save(customer);
    }

}
