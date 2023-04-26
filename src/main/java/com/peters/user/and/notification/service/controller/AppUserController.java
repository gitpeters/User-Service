package com.peters.user.and.notification.service.controller;

import com.peters.user.and.notification.service.dto.UserRequestDto;
import com.peters.user.and.notification.service.dto.UserResponseDto;
import com.peters.user.and.notification.service.entity.AppUser;
import com.peters.user.and.notification.service.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return ResponseEntity.ok().body(service.getAllUsers());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseEntity<UserResponseDto>> getUserById(@PathVariable("userId") Long userId){
       return ResponseEntity.ok().body(service.findUserById(userId));
    }

    @PostMapping("/user/save")
    public ResponseEntity<?> saveUser(@Validated @RequestBody AppUser user) {
        return service.saveUser(user);
    }

    @PatchMapping("/user/update/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("userId") Long userId, @RequestBody UserRequestDto request){
        return service.updateUserInfo(userId, request);
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(service.deleteUser(userId));
    }
}
