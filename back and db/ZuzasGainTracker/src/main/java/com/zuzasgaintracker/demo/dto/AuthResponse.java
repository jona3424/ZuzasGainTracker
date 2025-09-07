package com.zuzasgaintracker.demo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthResponse {
    String accessToken;
    String refreshToken;
    UserDto user;
    String tokenType; // "Bearer"
}
