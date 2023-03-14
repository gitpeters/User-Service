package com.peters.user.and.notification.service.repository;

import com.peters.user.and.notification.service.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findAppUserByName(String username);
    boolean existsByEmailOrUsername(String username, String user);
}
