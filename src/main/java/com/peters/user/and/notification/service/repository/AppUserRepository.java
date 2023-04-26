package com.peters.user.and.notification.service.repository;

import com.peters.user.and.notification.service.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findUserByUsername(String username);
}
