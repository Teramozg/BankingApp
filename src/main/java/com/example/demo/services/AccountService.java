package com.example.demo.services;

import com.example.demo.DTO.AddMoneyToAccount;
import com.example.demo.DTO.TransferCashFromAccount;
import com.example.demo.Exceptions.MyIllegalArgumentException;
import com.example.demo.models.Account;
import com.example.demo.repositories.BalanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {
    private final BalanceRepository balanceRepository;

    public AccountService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }


    public List<Account> getAll() {
        return balanceRepository.findAll();

    }

    public Account getAccountById(Integer id) {
        Optional<Account> byId = balanceRepository.findById(id);
        if (!byId.isPresent()) {
            MyIllegalArgumentException ex = new MyIllegalArgumentException();
            ex.setId(id);
            throw ex;
        }
        return byId.get();

    }

    public Account createAccount(Account account) {
        return balanceRepository.save(account);
    }

    public void addCashToAccount(AddMoneyToAccount addMoneyToAccount) {
        Account accountById = getAccountById(addMoneyToAccount.getId());
        Double cash1 = accountById.getCash();
        accountById.setCash(cash1 + addMoneyToAccount.getCash());
        balanceRepository.save(accountById);

    }

    public void transferCashFromOneToOther(TransferCashFromAccount transferCashFromAccount) {
        Account accountFrom = getAccountById(transferCashFromAccount.getIdFrom());
        if (accountFrom.getCash() >= transferCashFromAccount.getCash()) {
            double from = accountFrom.getCash() - transferCashFromAccount.getCash();
            accountFrom.setCash(from);
            Account accountTo = getAccountById(transferCashFromAccount.getIdTo());
            double to = accountTo.getCash() + transferCashFromAccount.getCash();
            accountTo.setCash(to);
            balanceRepository.save(accountFrom);
            balanceRepository.save(accountTo);
        } else {
            log.info("Недостаточно средств на счету №{}", accountFrom.getId()); //todo посмотреть, что такое логи и куда логи пишутся
        }
    }




}

