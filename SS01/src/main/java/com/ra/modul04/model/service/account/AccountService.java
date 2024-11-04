package com.ra.modul04.model.service.account;

import com.ra.modul04.model.entity.Account;
import com.ra.modul04.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountService  {
    List<Account> findAll();
    Account save(Account account);
    void delete(Integer id);
    Account findById(Integer id);
}
