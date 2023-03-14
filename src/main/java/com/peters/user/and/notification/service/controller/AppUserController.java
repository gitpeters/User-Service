package com.peters.user.and.notification.service.controller;

import com.peters.user.and.notification.service.model.AppUser;
import com.peters.user.and.notification.service.service.AppUserService;
import com.peters.user.and.notification.service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class AppUserController {

    @Autowired
    private AppUserService service;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUser(){
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<AppUser>> getUserByUsername(@PathVariable("userId") Long userId){
        Optional<AppUser> user = service.findUserById(userId);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @PostMapping("/user/save")
    public ResponseEntity<AppUser> saveUser(@Validated @RequestBody AppUser user) {
        AppUser newUser;
        if (service.isAppUserExist(user.getUsername(), user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            newUser = service.saveUser(user);
            notificationService.sendNotification(newUser);
        }
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/user/update/{userId}")
    public ResponseEntity<AppUser> updateUser(@PathVariable("userId") Long userId, @RequestBody AppUser user){
        return new ResponseEntity<>(service.updateUserInfo(userId, user), HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId){
        service.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
