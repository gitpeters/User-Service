package com.peters.user.and.notification.service;

import com.peters.user.and.notification.service.entity.AppUser;
import com.peters.user.and.notification.service.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class UserAndNotificationServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(UserAndNotificationServiceApplication.class, args);
	}

}
