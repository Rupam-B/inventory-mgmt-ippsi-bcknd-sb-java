package com.storeManagement.Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private final String jwt;
    private final Long userId;
    private final String userName;
}
