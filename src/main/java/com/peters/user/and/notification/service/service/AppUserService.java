package com.peters.user.and.notification.service.service;

import com.peters.user.and.notification.service.dto.UserRequestDto;
import com.peters.user.and.notification.service.dto.UserResponseDto;
import com.peters.user.and.notification.service.entity.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    List<UserResponseDto> getAllUsers();
    ResponseEntity<UserResponseDto> findUserById(Long id);

    ResponseEntity<UserResponseDto> saveUser(AppUser request);

    ResponseEntity<UserResponseDto> updateUserInfo(Long id, UserRequestDto request);

    ResponseEntity<UserResponseDto> deleteUser(Long id);
}
