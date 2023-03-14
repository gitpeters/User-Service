package com.peters.user.and.notification.service.service;

import com.peters.user.and.notification.service.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    List<AppUser> getAllUsers();
    Optional<AppUser> findUserById(Long id);

    AppUser saveUser(AppUser user);

    AppUser findUserByUsername(String username);
    AppUser updateUserInfo(Long id, AppUser user);

    boolean isAppUserExist(String username, String user);
    void deleteUser(Long id);
}
