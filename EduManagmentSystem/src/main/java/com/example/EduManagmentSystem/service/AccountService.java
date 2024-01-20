package com.example.EduManagmentSystem.service;

import com.example.EduManagmentSystem.enums.AccountType;
import com.example.EduManagmentSystem.model.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public Account getAccountDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return new Account(currentPrincipalName, AccountType.valueOf(currentPrincipalName.toUpperCase()));
    }
}
