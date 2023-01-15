package com.smitoi.roomatey.services;

import com.smitoi.roomatey.dto.requests.auth.LoginRequest;
import com.smitoi.roomatey.dto.requests.auth.RegisterRequest;
import com.smitoi.roomatey.dto.responses.AuthenticationResponse;
import com.smitoi.roomatey.entity.User;
import com.smitoi.roomatey.repository.UserRepository;
import com.smitoi.roomatey.security.JwtService;
import com.smitoi.roomatey.utils.UserMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("testing")
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testRegister() {
        RegisterRequest request = UserMocks.mockRegisterRequest();

        when(passwordEncoder.encode(request.getPassword())).thenReturn(request.getPassword());
        when(jwtService.generateToken(any())).thenReturn("token");

        AuthenticationResponse result = authenticationService.register(request);

        assertEquals(result.getToken(), "token");
    }


    @Test
    public void testLogin() {
        User user = UserMocks.mockUser();

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("token");

        LoginRequest request = LoginRequest.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        AuthenticationResponse result = authenticationService.login(request);

        assertEquals(result.getToken(), "token");
    }
}