package ru.itis.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int id;
    private String name;
}
