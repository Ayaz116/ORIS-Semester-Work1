package ru.itis.model;


import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Birthday {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Long userId;
    private int daysToBirthday;
    private int upcomingAge;
}