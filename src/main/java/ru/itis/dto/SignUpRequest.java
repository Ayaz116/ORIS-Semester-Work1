package ru.itis.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String email;
    private String password;
    private String nickname;
}
