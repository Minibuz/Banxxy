package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.CustomerEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.AccountDto;
import fr.esipe.banxxy.dto.AccountsChildrenDto;
import fr.esipe.banxxy.dto.AccountsParentDto;
import fr.esipe.banxxy.repository.AccountRepository;
import fr.esipe.banxxy.repository.AdvisorRepository;
import fr.esipe.banxxy.repository.CustomerRepository;
import fr.esipe.banxxy.repository.UserRepository;
import fr.esipe.banxxy.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountsServicesImpl implements AccountsService {

    private final UserRepository userRepository;
    private final AdvisorRepository advisorRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountsServicesImpl(UserRepository userRepository,
                                AdvisorRepository advisorRepository,
                                CustomerRepository customerRepository,
                                AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.advisorRepository = advisorRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
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
    public List<AccountsParentDto> getAccounts(Integer userId) {
        var user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
        // TODO - replace exception thrown by returning error to api
        if (isAdvisor(user))
            // TODO - replace exception thrown by returning error to api
            throw new IllegalArgumentException("The User is not a Customer");
        return getAllAccounts(userId);
    }

    @Override
    public List<AccountsParentDto> getAttachedAccounts(Integer userId) {
        // TODO - replace exception thrown by returning error to api
        var user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
        if (isCustomer(user))
            // TODO - replace exception thrown by returning error to api
            throw new IllegalArgumentException("The User is not an advisor");
        return getAllAccounts(userId);
    }

    private Set<AccountDto> getAccountsFromCustomer(CustomerEntity customer) {
        var accounts = new HashSet<AccountDto>();
        customer.getAccounts().forEach(account -> {
            accounts.add(new AccountDto(
                    "title",
                    account.getId(),
                    account.getBalance()
            ));
        });
        return accounts;
    }

    private Set<AccountsChildrenDto> getChildrensAccountsFromCustomer(CustomerEntity customer) {
        var childrens = new HashSet<AccountsChildrenDto>();
        customer.getChildrens().forEach(children -> {
            childrens.add(new AccountsChildrenDto(
                    children.getFirstname(),
                    children.getName(),
                    children.getId(),
                    getAccountsFromCustomer(children)
            ));
        });
        return childrens;
    }

    @Override
    public List<AccountsParentDto> getAllAccounts(Integer userId) {
        // TODO - replace exception thrown by returning error to api
        var user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
        Set<CustomerEntity> customers = getCustomersFromUser(user, userId);
        if (customers.isEmpty())
            // TODO - replace exception thrown by returning error to api
            throw new NoSuchElementException("No customer found for this user");

        var accountList = new ArrayList<AccountsParentDto>();

        customers.forEach(customer -> {
            var accounts = getAccountsFromCustomer(customer);
            var childrens = getChildrensAccountsFromCustomer(customer);

            accountList.add(
                    new AccountsParentDto(customer.getFirstname(),
                            customer.getName(),
                            customer.getAdvisor().getFirstname(),
                            customer.getAdvisor().getName(),
                            customer.getId(),
                            accounts,
                            childrens
                    )
            );
        });

        return accountList;
    }
}
