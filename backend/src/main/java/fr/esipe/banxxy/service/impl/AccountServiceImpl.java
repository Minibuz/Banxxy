package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AccountEntity;
import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.CustomerEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.AccountDetailledDto;
import fr.esipe.banxxy.dto.AccountDto;
import fr.esipe.banxxy.repository.AccountRepository;
import fr.esipe.banxxy.repository.AdvisorRepository;
import fr.esipe.banxxy.repository.CustomerRepository;
import fr.esipe.banxxy.repository.UserRepository;
import fr.esipe.banxxy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final AdvisorRepository advisorRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository,
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
            var advisor = advisorRepository.findById(Integer.toUnsignedLong(id)).orElseThrow();
            return advisor.getCustomers();
        } else if (user instanceof CustomerEntity)
            return Set.of(customerRepository.findById(Integer.toUnsignedLong(id)).orElseThrow());
        else return Set.of();
    }

    @Override
    public Optional<AccountDto> getAccountDetails(Integer accountId, Integer userId) {
        var user = userRepository.findById(Integer.toUnsignedLong(userId));
        if (user.isEmpty())
            return Optional.empty();
        var customers = getCustomersFromUser(user.get(), userId);
        if (customers.isEmpty())
            return Optional.empty();
        AccountEntity account;
        CustomerEntity customer;
        if (isAdvisor(user.get())) {
            account = customers.stream()
                    .map(CustomerEntity::getAccounts)
                    .flatMap(Collection::stream)
                    .filter(acc -> Objects.equals(acc.getId(), accountId.longValue()))
                    .findFirst()
                    .orElse(null);
        } else {
            customer = customers.iterator().next();
            account = customer.getAccounts().stream()
                    .filter(acc -> Objects.equals(acc.getId(), accountId.longValue()))
                    .findFirst()
                    .orElse(null);
        }
        if (account == null)
            return Optional.empty();
        return Optional.of(new AccountDto(
                account.getTitle(),
                account.getId(),
                account.getBalance()
        ));
    }

    @Override
    public boolean deleteAccount(Integer userId, Integer accountId) {
        var opt = userRepository.findById(Long.valueOf(userId));
        if (opt.isEmpty())
            return false;
        UserEntity user = opt.get();
        if (isAdvisor(user)) {
            AdvisorEntity advisor = advisorRepository.findById(Long.valueOf(userId)).orElse(null);
            if (advisor == null)
                return false;
            Set<CustomerEntity> customers = advisor.getCustomers();
            for (CustomerEntity customer : customers) {
                if (customer.getAccounts().removeIf(account -> account.getId().equals(Long.valueOf(accountId)))) {
                    accountRepository.deleteById(Long.valueOf(accountId));
                    if(!accountRepository.existsById(Long.valueOf(accountId)))
                        return true;
                }
            }
        } else if (isCustomer(user)) {
            CustomerEntity customer = customerRepository.findById(Long.valueOf(userId)).orElse(null);
            if (customer == null)
                return false;
            if(customer.getAccounts().removeIf(account -> account.getId().equals(Long.valueOf(accountId)))) {
                accountRepository.deleteById(Long.valueOf(accountId));
                return !accountRepository.existsById(Long.valueOf(accountId));
            }
        }
        return false;
    }


    @Override
    public Optional<AccountDetailledDto> createAccount(AccountDetailledDto accountDto) {
        var opt = customerRepository.findById(accountDto.getId_owner());
        if (opt.isEmpty())
            return Optional.empty();
        CustomerEntity customer = opt.get();
        AccountEntity account = new AccountEntity();
        account.setCustomer(customer);
        account.setBalance(BigInteger.valueOf(0));
        account.setTitle(accountDto.getTitle());
        account = accountRepository.save(account);
        AdvisorEntity advisor = advisorRepository.findByCustomersContaining(customer);
        String advisorFirstName = advisor.getFirstname();
        String advisorLastName = advisor.getName();
        return Optional.of(new AccountDetailledDto(
                account.getId(),
                accountDto.getTitle(),
                customer.getFirstname(),
                customer.getName(),
                advisorFirstName,
                advisorLastName,
                account.getBalance(),
                customer.getId()
        ));
    }
}