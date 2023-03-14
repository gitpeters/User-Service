package com.peters.user.and.notification.service.service;

import com.peters.user.and.notification.service.model.AppUser;

public interface NotificationService {
    void sendNotification(AppUser user);
}
