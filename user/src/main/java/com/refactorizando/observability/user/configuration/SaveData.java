package com.refactorizando.observability.user.configuration;

import com.refactorizando.observability.user.domain.UserDetail;
import com.refactorizando.observability.user.repository.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class SaveData implements CommandLineRunner {

    private final UserDetailRepository userDetailRepository;

    @Override
    public void run(String... args) {

        long leftLimit = 1L;
        long rightLimit = 100L;
        IntStream.range(0, 100).forEach(cont -> {
            userDetailRepository.saveAndFlush(UserDetail.builder().name(generateName())
                    .accountNumber(leftLimit + (long) (Math.random() * (rightLimit - leftLimit))).build());
        });


    }

    public String generateName() {

        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return "pepe " + random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
