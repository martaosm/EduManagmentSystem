package com.example.EduManagmentSystem.controller;

import com.example.EduManagmentSystem.model.Account;
import com.example.EduManagmentSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accountDetails")
    public ResponseEntity<String> getAccountDetails(){
        return ResponseEntity.ok(accountService.getAccountDetails().getUsername());
    }
}
