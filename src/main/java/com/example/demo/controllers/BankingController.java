package com.example.demo.controllers;

import com.example.demo.DTO.AddMoneyToAccount;
import com.example.demo.DTO.TransferCashFromAccount;
import com.example.demo.Exceptions.MyIllegalArgumentException;
import com.example.demo.models.Account;
import com.example.demo.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class BankingController  {
    private final AccountService accountService;

    public BankingController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getList")
    public List<Account> getAllBalance() {

        return accountService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Account getAccountById(@PathVariable Integer id) {
        Account accountById = accountService.getAccountById(id);
        log.info("getAccountById id={}", accountById.getId());
        return accountById;
    }

    @PutMapping("/addCashToAccount")
    public void addCashToAccount(@RequestBody AddMoneyToAccount addMoneyToAccount) {
        System.out.println();
        accountService.addCashToAccount(addMoneyToAccount);
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        if (account.getId() != null) {
            Account accountById = accountService.getAccountById(account.getId());
            if (accountById.getId() == null) {
                Account account1 = accountService.createAccount(account);
                log.info("createdAccount is {}", account1);
                return account1;
            } else {
                log.info("accountIdIsCreated");
                return accountById;
            }
        } else {
            Account account1 = accountService.createAccount(account);
            log.info("createdAccount is {}", account1);
            return account1;

        }
    }

    @PutMapping("/transferMoney")
    public void transferMoney(@RequestBody TransferCashFromAccount transferCashFromAccount){
        accountService.transferCashFromOneToOther(transferCashFromAccount);
    }

    @ExceptionHandler(MyIllegalArgumentException.class)
    public String stringHandel(MyIllegalArgumentException ex){
        log.error(ex.toString()); // лог в терминал,
        return ex.getMessage() ; // return возвращает сообщение пользлвателю
        //todo 1 изучить логирование 2 добавить логирование в файл уровня ошибки.
        //todo 3 изучить создание своего exception( и создать свой), 4 инфо по тестированию, 5 проект лессон перенести на мавен
    }


}

