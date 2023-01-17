package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.CustomerEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.AccountDto;
import fr.esipe.banxxy.repository.AdvisorRepository;
import fr.esipe.banxxy.repository.CustomerRepository;
import fr.esipe.banxxy.repository.UserRepository;
import fr.esipe.banxxy.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class AccountsServicesImpl implements AccountsService {

    private final UserRepository userRepository;
    private final AdvisorRepository advisorRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountsServicesImpl(UserRepository userRepository,
                                AdvisorRepository advisorRepository,
                                CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.advisorRepository = advisorRepository;
        this.customerRepository = customerRepository;
    }

    private boolean isAdvisor(UserEntity user) {
        return user instanceof AdvisorEntity;
    }

    private boolean isCustomer(UserEntity user) {
        return user instanceof CustomerEntity;
    }

    private Set<CustomerEntity> getCustomersFromUser(UserEntity user, int id) {
        if (isAdvisor(user)) {
            // TODO - replace exception thrown by returning error to api
            var advisor = advisorRepository.findById((long) id).orElseThrow();
            return advisor.getCustomers();
        } else if (isCustomer(user))
            // TODO - replace exception thrown by returning error to api
            return Set.of(customerRepository.findById((long) id).orElseThrow());
        else return Set.of();
    }

    @Override
    public List<AccountDto> getAccounts(Integer userId) {
        var user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
        // TODO - replace exception thrown by returning error to api
        if (isAdvisor(user))
            // TODO - replace exception thrown by returning error to api
            throw new IllegalArgumentException("The User is not a Customer");
        return getAllAccounts(userId);
    }

    @Override
    public List<AccountDto> getAttachedAccounts(Integer userId) {
        // TODO - replace exception thrown by returning error to api
        var user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
        if (isCustomer(user))
            // TODO - replace exception thrown by returning error to api
            throw new IllegalArgumentException("The User is not an advisor");
        return getAllAccounts(userId);
    }

    @Override
    public List<AccountDto> getAllAccounts(Integer userId) {
        // TODO - replace exception thrown by returning error to api
        var user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
        var accountList = new ArrayList<AccountDto>();
        Set<CustomerEntity> customers = getCustomersFromUser(user, userId);
        if (customers.isEmpty())
            // TODO - replace exception thrown by returning error to api
            throw new NoSuchElementException("No customer found for this user");

        customers.forEach(customer -> customer.getAccounts().forEach(account -> accountList.add(
                new AccountDto(account.getCustomer().getFirstname(),
                        account.getCustomer().getName(),
                        account.getCustomer().getAdvisor().getFirstname(),
                        account.getCustomer().getAdvisor().getName(),
                        account.getBalance().longValue()
                )
        )));
        return accountList;
    }
}
