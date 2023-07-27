package com.example.payment.dao;

import com.example.payment.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDao extends JpaRepository<Account,Integer> {
    Optional<Account> findByEmail(String email);
}
