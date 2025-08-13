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

    public Plant(String plantId, Object o, Object o1, Object o2, Object o3, String s) {
        this.plantId = plantId;
        this.name = o.toString();
        this.waterType = o1.toString();
        this.tankId = o2.toString();
        this.size = o3.toString();
        this.quantity = s;
    }

    public Plant(String name) {
        this.name = name;
    }
}
