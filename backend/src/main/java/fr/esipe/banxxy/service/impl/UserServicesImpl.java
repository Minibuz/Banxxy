package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.CustomerEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.user.UserDetailDto;
import fr.esipe.banxxy.dto.user.UserDto;
import fr.esipe.banxxy.dto.user.UserReceivedDto;
import fr.esipe.banxxy.repository.AdvisorRepository;
import fr.esipe.banxxy.repository.CustomerRepository;
import fr.esipe.banxxy.repository.UserRepository;
import fr.esipe.banxxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    /**
     * Return the advisor associated with the userId
     * @param userId the id used to find the advisor
     * @return the advisor if exists, otherwise null
     */
    private AdvisorEntity getAdvisor(Long userId) {
        return advisorRepository.findById(userId).orElse(null);
    }

    /**
     * Get the customer associated with the userId
     * @param userId the id used to find the customer
     * @return the customer if exists, otherwise null
     */
    private CustomerEntity getCustomer(Long userId) {
        return customerRepository.findById(userId).orElse(null);
    }

    /**
     * Get the list of customers associated with the advisor given
     * @param advisor the advisor who manages the customers
     * @return the list of customers associated with the advisor
     */
    private List<UserDto> getCustomerList(AdvisorEntity advisor) {
        List<UserDto> customerDtoList = new ArrayList<>();
        var customers = advisor.getCustomers();
        customers.forEach(customerEntity -> customerDtoList.add(new UserDto(
                customerEntity.getFirstname(),
                customerEntity.getName(),
                customerEntity.getId())));
        return customerDtoList;
    }

    /**
     * Get the list of children associated with the customer given
     * @param customer the customer responsible for the children
     * @return the list of children associated with the customer
     */
    private List<UserDto> getChildrenList(CustomerEntity customer) {
        List<UserDto> childrenDtoList = new ArrayList<>();
        var children = customer.getChildrens();
        children.forEach(customerEntity -> childrenDtoList.add(new UserDto(
                customerEntity.getFirstname(),
                customerEntity.getName(),
                customerEntity.getId())));
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
        return Collections.emptyList();
    }

    /**
     * Create a userDetailDto using user given
     * @param user the user having datas to use
     * @return the object containing datas
     */
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
        return null;

    }

    @Override
    public Optional<UserEntity> createCustomer(UserReceivedDto userReceivedDto) {
        CustomerEntity customer = new CustomerEntity();
        customer.setAdvisor(getAdvisor(userReceivedDto.getAdvisorIdAsLong()));
        customer.setFirstname(userReceivedDto.getFirstName());
        customer.setName(userReceivedDto.getLastName());
        customer.setUsername(userReceivedDto.getUserName());
        //customer.setPassword(userReceivedDto.getPassword());
        customer.setPassword("{bcrypt}$2a$10$VCIeTiINf5oL9grYi/cnN.W7xssZjHgzDBK7F8oD14ndZUVifhjTK");
        customer.setRole("ROLE_CUSTOMER");
        customer.setMail(userReceivedDto.getMail());
        customer.setType("customer");
        customer.setAdvisor(getAdvisor(userReceivedDto.getAdvisorIdAsLong()));
        customer = customerRepository.save(customer);
        return userRepository.findById(customer.getId());
    }

    @Override
    public Optional<UserEntity> createAdvisor(UserReceivedDto userReceivedDto) {
        AdvisorEntity advisor = new AdvisorEntity();
        advisor.setFirstname(userReceivedDto.getFirstName());
        advisor.setName(userReceivedDto.getLastName());
        advisor.setUsername(userReceivedDto.getUserName());
        advisor.setPassword(userReceivedDto.getPassword());
        advisor.setRole("ROLE_ADVISOR");
        advisor.setMail(userReceivedDto.getMail());
        advisor.setType("advisor");
        advisor = advisorRepository.save(advisor);
        return userRepository.findById(advisor.getId());
    }

    @Override
    public Boolean deleteUser(Long userId) {
        var user = userRepository.findById(userId);
        if (user.isEmpty())
            return false;
        userRepository.delete(user.get());
        if(!userRepository.findById(userId).equals(Optional.empty()))
            return false;
        switch (user.get().getType()) {
            case "advisor" -> {
                advisorRepository.delete(getAdvisor(userId));
                return advisorRepository.findById(userId).equals(Optional.empty());
            }
            case "customer" -> {
                customerRepository.delete(getCustomer(userId));
                return customerRepository.findById(userId).equals(Optional.empty());
            }
            default -> {
                return false;
            }
        }
    }

}
