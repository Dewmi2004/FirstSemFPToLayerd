package org.example.firstsemfptolayerd.view.tdm;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class FoodTM {
    private String foodId;
    private String name;
    private String fishType;
    private Date exDate;
    private String quantity;
}
