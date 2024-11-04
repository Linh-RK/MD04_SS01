package com.ra.modul04.controller;

import com.ra.modul04.model.entity.Account;
import com.ra.modul04.model.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    @Autowired
   public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String index(Model model) {
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts",accounts);
        return "account/account";
    }
    //    ADD-----------------------------------
     @GetMapping("/add")
     public String add(Model model) {
        model.addAttribute("account", new Account());
        return "account/add";
     }

    @PostMapping("/add")
    public String add(@ModelAttribute Account account, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("account", account);
            return "/account/add";
        }
        if(accountService.save(account) != null) {
            return "redirect:/account";
        }
        model.addAttribute("account", accountService.findAll());
        return "/account/add";
    }
    //    EDIT-----------------------------------
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Account account = accountService.findById(id);
        model.addAttribute("account", account);
        return "account/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Account account, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("account", account);
            return "account/edit";
        }
        if(accountService.save(account) != null) {
            return "redirect:/account";
        }
        model.addAttribute("account", accountService.findAll());
        return "account/edit";
    }

    //    DELETE-----------------------------------
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        accountService.delete(id);
        return "redirect:/account";
    }



}