package com.refactorizando.observability.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String name;

    private int age;

    private Account account;
}
