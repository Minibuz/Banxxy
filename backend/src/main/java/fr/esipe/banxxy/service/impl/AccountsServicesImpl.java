package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.CustomerEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.AccountDetailledDto;
import fr.esipe.banxxy.repository.AdvisorRepository;
import fr.esipe.banxxy.repository.CustomerRepository;
import fr.esipe.banxxy.repository.UserRepository;
import fr.esipe.banxxy.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
            var advisor = advisorRepository.findById((long) id).orElseThrow();
            return advisor.getCustomers();
        } else if (isCustomer(user))
            return Set.of(customerRepository.findById((long) id).orElseThrow());
        else return Set.of();
    }

    @Override
    public List<AccountDetailledDto> getAccounts(Integer userId) {
        var opt = userRepository.findById(Long.valueOf(userId));
        if (opt.isEmpty() || isAdvisor(opt.get()))
            return List.of();
        return getAllAccounts(userId);
    }

    private void addCustomerAccountsToList(CustomerEntity customer, List<AccountDetailledDto> accountsList) {
        customer.getAccounts().forEach(account ->
                accountsList.add(new AccountDetailledDto(
                        account.getId(),
                        account.getTitle(),
                        customer.getFirstname(),
                        customer.getName(),
                        customer.getAdvisor().getFirstname(),
                        customer.getAdvisor().getName(),
                        account.getBalance(),
                        customer.getId()
                ))
        );
    }

    private void addChildrenAccountsToList(CustomerEntity customer, List<AccountDetailledDto> accountsList) {
        customer.getChildrens().forEach(children -> children.getAccounts().forEach(account ->
                accountsList.add(new AccountDetailledDto(
                        account.getId(),
                        account.getTitle(),
                        children.getFirstname(),
                        children.getName(),
                        children.getAdvisor().getFirstname(),
                        children.getAdvisor().getName(),
                        account.getBalance(),
                        children.getId()
                ))
        ));
    }

    @Override
    public List<AccountDetailledDto> getAttachedAccounts(Integer userId) {
        var opt = userRepository.findById(Long.valueOf(userId));
        if (opt.isEmpty() || isCustomer(opt.get()))
            return List.of();
        return getAllAccounts(userId);
    }

    @Override
    public List<AccountDetailledDto> getAllAccounts(Integer userId) {
        var opt = userRepository.findById(Long.valueOf(userId));
        if (opt.isEmpty())
            return List.of();
        var user = opt.get();
        Set<CustomerEntity> customers = getCustomersFromUser(user, userId);
        if (customers.isEmpty())
            return List.of();
        var accountsList = new ArrayList<AccountDetailledDto>();
        customers.forEach(customer -> {
            addCustomerAccountsToList(customer, accountsList);
            if (isCustomer(user)) {
                addChildrenAccountsToList(customer, accountsList);
            }
        });
        return accountsList;
    }
}
