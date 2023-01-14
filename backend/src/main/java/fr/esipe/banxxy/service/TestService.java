package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.TestDto;
import fr.esipe.banxxy.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final AccountRepository repository;

    @Autowired
    TestService(AccountRepository repository) {
        this.repository = repository;
    }

    public TestDto findTest() {
        return new TestDto("It work");
    }
}
