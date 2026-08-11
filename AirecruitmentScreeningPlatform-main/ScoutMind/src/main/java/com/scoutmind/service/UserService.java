package com.scoutmind.service;

import com.scoutmind.dto.UserCreateDTO;
import com.scoutmind.dto.UserResponseDTO;
import com.scoutmind.dto.UserUpdateDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserCreateDTO dto);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO getUserByEmail(String email);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(Long id, UserUpdateDTO dto);

    void deleteUser(Long id);
}
