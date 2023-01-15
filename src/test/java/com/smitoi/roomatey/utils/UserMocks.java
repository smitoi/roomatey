package com.smitoi.roomatey.utils;

import com.smitoi.roomatey.dto.requests.auth.RegisterRequest;
import com.smitoi.roomatey.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMocks {

    public static User mockUser() {
        return User.builder()
                .id(1L)
                .name("roomatey")
                .email("roomatey@testing.com")
                .password("$2a$10$Xw1HrnBfmCW8.aUnbBbFMettEZPF9NE6JGwmSEYRkWT1kmm8IsM4u") // secret
                .build();
    }

    public static RegisterRequest mockRegisterRequest() {
        return RegisterRequest.builder()
                .email("roomatey@test.com")
                .password("secret")
                .name("Roomatey Test")
                .build();
    }
}
