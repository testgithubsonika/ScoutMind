package com.scoutmind.service;

import com.scoutmind.dto.AuthResponseDTO;
import com.scoutmind.dto.LoginRequestDTO;

public interface AuthService {
    AuthResponseDTO login(LoginRequestDTO loginRequestInfo);
}
