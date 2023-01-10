package com.smitoi.roomatey.utils;

import com.smitoi.roomatey.dto.requests.auth.LoginRequest;
import com.smitoi.roomatey.dto.requests.auth.RegisterRequest;
import com.smitoi.roomatey.dto.responses.GroupDto;
import com.smitoi.roomatey.entity.Group;
import com.smitoi.roomatey.entity.User;

public class UserMocks {

    public static LoginRequest mockLoginRequest() {
        return LoginRequest.builder()
                .email("roomatey@test.com")
                .password("secret")
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
