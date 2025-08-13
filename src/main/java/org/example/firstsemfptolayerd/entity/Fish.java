package org.example.firstsemfptolayerd.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Fish {
    private String fishId;
    private String name;
    private String size;
    private String tankId;
    private String gender;
    private String waterType;
    private String country;
    private String colour;
    private String quantity;

    public Fish(String name) {
        this.name = name;
    }
}