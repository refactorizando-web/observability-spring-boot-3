package com.refactorizando.observability.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Account {

    private Long accountId;

    private String title;

    private String description;

    private BigDecimal amount;
}
