package org.example.firstsemfptolayerd.entity;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private String inventoryId;
    private String supId;
    private String date;
    private Button btn;
    private String quantity;
    private String unitPrice;
    private String itemId;
    private String fishId;
    private String foodId;
    private String chemicalId;
    private String plantId;
    public Inventory(String inventoryId, String date, String supId, String itemId) {
        this.inventoryId = inventoryId;
        this.date = date;
        this.supId = supId;
        this.itemId = itemId;
    }
}
