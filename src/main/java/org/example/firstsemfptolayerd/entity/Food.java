package org.example.firstsemfptolayerd.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Food {
    private String foodId;
    private String name;
    private String fishType;
    private Date exDate;
    private String quantity;
}
