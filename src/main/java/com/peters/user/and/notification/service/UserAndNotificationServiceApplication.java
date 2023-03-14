package com.peters.user.and.notification.service;

import com.peters.user.and.notification.service.model.AppUser;
import com.peters.user.and.notification.service.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
@SpringBootApplication
public class UserAndNotificationServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(UserAndNotificationServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner run(AppUserService appUserService) {
		return args -> {
			AppUser user = new AppUser(null, "Peter Abraham", "depitaztech@gmail.com","depitaztech@gmail.com", "08036770752","1234");
			try {
				if (appUserService.isAppUserExist(user.getUsername(), user.getEmail())) {
					throw new IllegalStateException("User already exists");
				} else {
					appUserService.saveUser(user);
				}
			} catch (IllegalStateException e) {
				// Handle the exception appropriately, for example by logging an error message
				log.info("Error:: {}", e.getMessage());
			}
		};
	}
}
