package org.example.firstsemfptolayerd.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private String inventoryId;
    private String supId;
    private String date;
}
