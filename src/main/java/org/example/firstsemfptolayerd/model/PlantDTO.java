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
}
