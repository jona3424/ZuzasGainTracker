package com.zuzasgaintracker.demo.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UserDto {
    UUID id;
    String email;
    String firstName;
    String lastName;
}
