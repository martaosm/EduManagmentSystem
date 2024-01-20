package com.example.EduManagmentSystem.model;

import com.example.EduManagmentSystem.enums.AccountType;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Account {

    Long id;
    String username;
    String password;
    AccountType accountType;
    Long personalDataId;

    public Account(String username, AccountType accountType) {
        this.username = username;
        this.accountType = accountType;
    }
}
