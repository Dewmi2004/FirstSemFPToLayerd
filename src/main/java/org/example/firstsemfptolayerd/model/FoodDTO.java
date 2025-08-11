package org.example.firstsemfptolayerd.model;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class FoodDTO {
    private String foodId;
    private String name;
    private String fishType;
    private Date exDate;
    private String quantity;
}
