package com.refactorizando.observability.user.resource;

import com.refactorizando.observability.user.dto.Account;
import com.refactorizando.observability.user.dto.User;
import com.refactorizando.observability.user.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getUserInfo(@PathVariable long accountId) {

        log.info("Get Account info by accountID {}", accountId);

        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

}
