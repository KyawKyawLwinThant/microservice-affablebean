package com.example.payment.service;

import com.example.payment.dao.AccountDao;
import com.example.payment.entity.Account;
import com.example.payment.exception.InsuffientAmountException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/*
getBalance
withdraw
deposit
transfer
 */
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountDao accountDao;

    public double getBalance(String email){
        return getByEmail(email)
                .orElseThrow(EntityNotFoundException::new)
                .getAmount();
    }
    @Transactional
    public double deposit(String email,double amount){
        double balance = getBalance(email);
        double updatedAmount = (balance + amount);
        Optional<Account> accountOptional = getByEmail(email);
        if(accountOptional.isPresent()){
            Account account= accountOptional.get();
            account.setAmount(updatedAmount);
            accountDao.save(account);
            return updatedAmount;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    @Transactional
    public double withdraw(String email,double amount){
        double balance = getBalance(email);
        if(balance < amount){
            throw new InsuffientAmountException();
        }
        double updatedAmount =  balance - amount;
        Optional<Account> accountOptional= getByEmail(email);
        if(accountOptional.isPresent()){
            Account account=accountOptional.get();
            account.setAmount(updatedAmount);
            accountDao.save(account);
            return updatedAmount;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    public void transfer(String fromEmail,String toEmail,double amount){
        withdraw(fromEmail,amount);
        deposit(toEmail,amount);
    }

    private Optional<Account> getByEmail(String email) {
        return accountDao.findByEmail(email);
    }

}
