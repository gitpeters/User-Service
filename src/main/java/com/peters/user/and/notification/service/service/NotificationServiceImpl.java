package com.peters.user.and.notification.service.service;

import com.peters.user.and.notification.service.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendNotification(AppUser user) {
        SimpleMailMessage mail = new SimpleMailMessage();
        String message = "Welcome to acis space "+user.getName()+"\n"+"Below is your login details:\n username: "+user.getUsername()+"\n password: "+user.getPassword();
        mail.setTo(user.getEmail());
        mail.setSubject("New user Notification");
        mail.setText(message);
        mailSender.send(mail);
    }
}
