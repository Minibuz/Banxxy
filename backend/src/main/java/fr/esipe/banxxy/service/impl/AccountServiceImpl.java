package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AccountEntity;
import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.CustomerEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.AccountDto;
import fr.esipe.banxxy.repository.AdvisorRepository;
import fr.esipe.banxxy.repository.CustomerRepository;
import fr.esipe.banxxy.repository.UserRepository;
import fr.esipe.banxxy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final AdvisorRepository advisorRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository,
                              AdvisorRepository advisorRepository,
                              CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.advisorRepository = advisorRepository;
        this.customerRepository = customerRepository;
    }

    private boolean isAdvisor(UserEntity user) {
        return user instanceof AdvisorEntity;
    }

    private Optional<Set<CustomerEntity>> getCustomersFromUser(UserEntity user, int id) {
        if (isAdvisor(user)) {
            var advisor = advisorRepository.findById(id).orElseThrow();
            return Optional.of(advisor.getCustomers());
        } else if (user instanceof CustomerEntity)
            return Optional.of(Set.of(customerRepository.findById(id).orElseThrow()));
        else return Optional.empty();
    }

    @Override
    public AccountDto getAccountDetails(Integer accountId, Integer userId) {
        if(userId == 0)
            return new AccountDto("Léo", "Barroux","Christine","LeMaraicher",100L);

        AccountEntity account;
        CustomerEntity customer;
        var user = userRepository.findById(userId).orElseThrow();
        if (user instanceof AdvisorEntity)
            throw new IllegalArgumentException("Id should be a Customer, not an Advisor");
        var customers = getCustomersFromUser(user, userId).orElseThrow();
        if (isAdvisor(user)) {
            account = customers.stream()
                    .map(CustomerEntity::getAccounts)
                    .flatMap(Collection::stream)
                    .filter(acc -> Objects.equals(acc.getId(), accountId))
                    .findFirst()
                    .orElse(null);
            if (account == null)
                throw new IllegalStateException("The account does not correspond to a Customer for this Advisor");
            customer = account.getCustomer();
        } else {
            customer = customers.iterator().next();
            account = customer.getAccounts().stream()
                    .filter(acc -> Objects.equals(acc.getId(), accountId))
                    .findFirst()
                    .orElse(null);
            if (account == null)
                throw new IllegalStateException("The account does not correspond to any of yours");
        }
        var advisor = customer.getAdvisor();
        return new AccountDto(customer.getFirstname(),
                customer.getName(),
                advisor.getFirstname(),
                advisor.getName(),
                account.getBalance());
    }
}