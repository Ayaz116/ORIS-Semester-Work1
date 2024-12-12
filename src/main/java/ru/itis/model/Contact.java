package ru.itis.model;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private int id;
    private String name;
    private Date birthDate;
}
