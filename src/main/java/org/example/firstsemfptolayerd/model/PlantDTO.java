package org.example.firstsemfptolayerd.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class PlantDTO {
    private String plantId;
    private String name;
    private String waterType;
    private String tankId;
    private String size;
    private String quantity;

    public PlantDTO(String plantId) {
        this.plantId = plantId;
    }

    public PlantDTO(String plantId, Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, String s) {
        this.plantId = plantId;
        this.name = o.toString();
        this.waterType = o1.toString();
        this.tankId = o2.toString();
        this.size = o3.toString();
        this.quantity = s;

    }
}
