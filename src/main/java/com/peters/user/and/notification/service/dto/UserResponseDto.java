package com.peters.user.and.notification.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class UserResponseDto {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String status;
    private String statusMessage;

    public UserResponseDto(String status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }
}
