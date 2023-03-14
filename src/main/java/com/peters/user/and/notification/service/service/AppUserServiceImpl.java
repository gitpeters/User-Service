package com.peters.user.and.notification.service.service;

import com.peters.user.and.notification.service.model.AppUser;
import com.peters.user.and.notification.service.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    private AppUserRepository repository;

    @Override
    public List<AppUser> getAllUsers()
    {
        log.info("Fetching all users..");
        return repository.findAll();
    }

    @Override
    public Optional<AppUser> findUserById(Long id) {
        AppUser appUser = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found with ID: " + id));
        log.info("Fetching user by userId {}", id);
        return Optional.ofNullable(appUser);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("...saving {}", user);
        return repository.save(user);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        log.info("fetching {} ", username);
        return repository.findAppUserByName(username);
    }

    @Override
    public AppUser updateUserInfo(Long id, AppUser user) {
        AppUser existingUser = repository.findById(id)
                        .orElseThrow(()-> new RuntimeException("User with user id:: "+id+" not found"));

        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        existingUser.setUsername(user.getUsername());
        existingUser.setPhone(user.getPhone());

        AppUser updateUser = repository.save(existingUser);
        log.info("{} updated...", updateUser);
        return updateUser;
    }

    @Override
    public boolean isAppUserExist(String username, String useremail) {
        return repository.existsByEmailOrUsername(username,useremail);
    }

    @Override
    public void deleteUser(Long id) {
        AppUser appUser = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found with ID: " + id));
        repository.delete(appUser);
    }
}
