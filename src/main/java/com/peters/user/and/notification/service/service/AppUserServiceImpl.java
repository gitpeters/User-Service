package com.peters.user.and.notification.service.service;

import com.peters.user.and.notification.service.dto.UserRequestDto;
import com.peters.user.and.notification.service.dto.UserResponseDto;
import com.peters.user.and.notification.service.entity.AppUser;
import com.peters.user.and.notification.service.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    private AppUserRepository repository;
    @Value("${regex.email}")
    private String email;
    @Value("${regex.password}")
    private String password;
    @Value("${regex.phone}")
    private String mobileNo;

    private final NotificationService notificationService;

    @Override
    public ResponseEntity<UserResponseDto> saveUser(AppUser request) {
        Optional<AppUser> appUserOpt = repository.findUserByUsername(request.getUsername());
        if(appUserOpt.isPresent()){
            return ResponseEntity.badRequest().body(new UserResponseDto(String.valueOf(HttpStatus.BAD_REQUEST), "User already exist"));
        }
        if(!isInputValid(request.getEmail(), email)){
            return ResponseEntity.badRequest().body(new UserResponseDto(String.valueOf(HttpStatus.BAD_REQUEST), "Provide valid email address"));
        }

        if(isNullOrEmpty(request.getEmail())){
            return ResponseEntity.badRequest().body(new UserResponseDto(String.valueOf(HttpStatus.BAD_REQUEST), "This field cannot be empty"));
        }

        if(isNullOrEmpty(request.getPassword())){
            return ResponseEntity.badRequest().body(new UserResponseDto(String.valueOf(HttpStatus.BAD_REQUEST), "This field cannot be empty"));
        }

        if(!isInputValid(request.getPassword(), password)){
            return ResponseEntity.badRequest().body(new UserResponseDto(String.valueOf(HttpStatus.BAD_REQUEST), "Provide valid password"));
        }
        AppUser user = AppUser.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .build();
        repository.save(user);
        notificationService.sendNotification(user);
        return ResponseEntity.ok().body(new UserResponseDto(String.valueOf(HttpStatus.CREATED), "User successfully created"));
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUserInfo(Long id, UserRequestDto request) {
        Optional<AppUser> userOpt = repository.findById(id);
        if(!userOpt.isPresent()){
            return ResponseEntity.badRequest().body(new UserResponseDto(String.valueOf(HttpStatus.BAD_REQUEST), "User does not exist"));
        }

        AppUser existingUser = userOpt.get();

        existingUser.setEmail(request.getEmail());
        existingUser.setName(request.getName());
        existingUser.setPassword(request.getPassword());
        existingUser.setUsername(request.getUsername());
        existingUser.setPhone(request.getPhone());

        AppUser updateUser = repository.save(existingUser);
        log.info("{} updated...", updateUser);
        return ResponseEntity.ok().body(new UserResponseDto(String.valueOf(HttpStatus.ACCEPTED), "User records successfully updated"));
    }

    @Override
    public List<UserResponseDto> getAllUsers()
    {
        List<AppUser> users = repository.findAll();
        return users.stream()
                .map(this::mapToResponse).collect(Collectors.toList());

    }

    @Override
    public ResponseEntity<UserResponseDto> findUserById(Long id) {
        Optional<AppUser> appUserOpt = repository.findById(id);
        if(!appUserOpt.isPresent()){
            return ResponseEntity.badRequest().body(new UserResponseDto(String.valueOf(HttpStatus.BAD_REQUEST), "User does not exits"));
        }
        AppUser user = appUserOpt.get();
        UserResponseDto response = UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .username(user.getUsername())
                .build();
        return ResponseEntity.ok().body(response);
    }


    @Override
    public ResponseEntity<UserResponseDto> deleteUser(Long id) {
        Optional<AppUser> appUserOpt = repository.findById(id);
        if(!appUserOpt.isPresent()){
            return ResponseEntity.badRequest().body(new UserResponseDto(String.valueOf(HttpStatus.BAD_REQUEST), "User does not exits"));
        }
        AppUser user = appUserOpt.get();
        repository.delete(user);
        return ResponseEntity.ok().body(new UserResponseDto(String.valueOf(HttpStatus.ACCEPTED), "User with user id::"+id+" was deleted"));
    }

    private UserResponseDto mapToResponse(AppUser appUser) {
        return UserResponseDto.builder()
                .name(appUser.getName())
                .email(appUser.getEmail())
                .phone(appUser.getPhone())
                .username(appUser.getUsername())
                .build();
    }

    private boolean isInputValid(String input, String regex){
        return Pattern.compile(regex)
                .matcher(input)
                .matches();
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
