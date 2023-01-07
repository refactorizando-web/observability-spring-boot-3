package com.refactorizando.observability.user.service;

import com.refactorizando.observability.user.domain.AccountDetail;
import com.refactorizando.observability.user.dto.Account;
import com.refactorizando.observability.user.mapper.AccountMapper;
import com.refactorizando.observability.user.repository.AccountDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {

    private final AccountDetailRepository accountDetailRepository;

    private final AccountMapper accountMapper;


    public Account getAccountById(Long accountId) {

        log.info("Getting account by id {}", accountId);

        AccountDetail accountDetail = accountDetailRepository.getReferenceById(accountId);

        try {

            return accountMapper.toDto(accountDetail);

        } catch (EntityNotFoundException ex) {
            log.debug("Account not found, getting first {} ", accountId);

            accountDetail = accountDetailRepository.findAll().get(0);

        }

        return accountMapper.toDto(accountDetail);

    }

}
