package ru.itis.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private int status;
    private String statusDesc;
    private UserDataResponse user;
}