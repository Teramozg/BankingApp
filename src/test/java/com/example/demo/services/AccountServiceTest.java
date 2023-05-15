package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.repositories.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
@Autowired
    private final BalanceRepository balanceRepository;


    AccountServiceTest(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }


    @Test
    void getAccountById() {
//       when(balanceRepository.findById(3)).thenReturn(Optional.of(new Account(3, 3, 1000.00)));
        AccountService accountService=new AccountService(balanceRepository);
        Account accountById = accountService.getAccountById(3);
        assertEquals(3, accountById.getNumber());
    }
}