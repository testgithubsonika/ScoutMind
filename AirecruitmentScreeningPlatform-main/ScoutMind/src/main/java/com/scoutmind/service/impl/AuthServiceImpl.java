package com.scoutmind.service.impl;

import com.scoutmind.dto.AuthResponseDTO;
import com.scoutmind.dto.LoginRequestDTO;
import com.scoutmind.entity.User;
import com.scoutmind.exception.ResourceNotFoundException;
import com.scoutmind.mapper.UserMapper;
import com.scoutmind.repository.UserRepository;
import com.scoutmind.security.JwtTokenProvider;
import com.scoutmind.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestInfo) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestInfo.email(),
                        loginRequestInfo.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        User user = userRepository.findByEmail(loginRequestInfo.email())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with email: " + loginRequestInfo.email()));

        return new AuthResponseDTO(token, userMapper.toDTO(user));
    }
}
