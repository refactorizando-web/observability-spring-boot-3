package com.refactorizando.observability.user.resource;

import com.refactorizando.observability.user.domain.UserDetail;
import com.refactorizando.observability.user.dto.User;
import com.refactorizando.observability.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;



    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserInfo(@PathVariable long userId) {

        log.debug("Getting user info by userId {} ", userId);

        return ResponseEntity.ok(userService.getUserDetailByAccountId(userId));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUserInfo(@RequestParam String name, @RequestParam int page) {

        return ResponseEntity.ok(userService.findUserPageable(name, page));
    }
}
