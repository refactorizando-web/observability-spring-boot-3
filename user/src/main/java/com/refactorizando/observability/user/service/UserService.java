package com.refactorizando.observability.user.service;

import com.refactorizando.observability.user.configuration.WebClientConfig;
import com.refactorizando.observability.user.domain.UserDetail;
import com.refactorizando.observability.user.dto.Account;
import com.refactorizando.observability.user.dto.User;
import com.refactorizando.observability.user.mapper.UserMapper;
import com.refactorizando.observability.user.repository.UserDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final WebClientConfig webClientConfig;

    private final UserDetailRepository userDetailRepository;

    private final UserMapper userMapper;


    private final RestTemplate restTemplate;

    public List<User> findUserPageable(String name, int initPage) {

        Pageable page = PageRequest.of(initPage, 10);

        List<UserDetail> userDetail = userDetailRepository.findAllByName(name, page);

        return userDetail.stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());

    }

    public User getUserDetailByAccountId(Long userId) {

        log.info("Getting account detail by userId {} ", userId);

        UserDetail userDetail = userDetailRepository.getReferenceById(userId);

        Account accountDetail = restTemplate.getForEntity("http://127.0.0.1:8080/accounts/" +
                userDetail.getAccountNumber(), Account.class).getBody();

        //Use with webclient
        //Account accountDetail = getAccountDetailByAccountId(userDetail.getAccountNumber());

        User user = userMapper.toDto(userDetail);
        user.setAccount(accountDetail);

        return user;
    }

    private Account getAccountDetailByAccountId(Long account) {
        WebClient.RequestHeadersSpec<?> response =
                webClientConfig.webClientConfig().get().uri("accounts/"+account);

        return response.retrieve().bodyToMono(Account.class).block();
    }
}
