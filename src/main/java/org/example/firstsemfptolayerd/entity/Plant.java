package org.example.firstsemfptolayerd.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Plant {
    private String plantId;
    private String name;
    private String waterType;
    private String tankId;
    private String size;
    private String quantity;

}
