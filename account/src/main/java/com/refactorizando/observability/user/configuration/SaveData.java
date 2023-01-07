package com.refactorizando.observability.user.configuration;

import com.refactorizando.observability.user.domain.AccountDetail;
import com.refactorizando.observability.user.repository.AccountDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class SaveData implements CommandLineRunner {

    private final AccountDetailRepository accountDetailRepository;

    @Override
    public void run(String... args) {

        IntStream.range(0, 100).forEach(cont -> {
            accountDetailRepository.saveAndFlush(AccountDetail.builder().amount(generateNumber()).build());
        });


    }

    public static BigDecimal generateNumber() {
        BigDecimal min = new BigDecimal(0);
        BigDecimal max = new BigDecimal(10000);

        int digitCount = Math.max(min.precision(), max.precision());
        int bitCount = (int) (digitCount / Math.log10(2.0));

        BigDecimal alpha = new BigDecimal(
                new BigInteger(bitCount, new Random())
        ).movePointLeft(digitCount);

        return min.add(max.subtract(min).multiply(alpha, new MathContext(digitCount)));
    }
}
