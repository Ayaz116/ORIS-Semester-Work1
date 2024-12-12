package ru.itis.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataResponse {
    private Long id;
    private String email;
    private String nickname;
}
