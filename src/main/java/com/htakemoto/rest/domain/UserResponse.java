package com.htakemoto.rest.domain;

import lombok.Data;

@Data
public class UserResponse {
    private long userId;
    private String firstname;
    private String lastname;
}
