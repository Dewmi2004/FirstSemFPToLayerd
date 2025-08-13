package org.example.firstsemfptolayerd.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryTM {
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

    public InventoryTM(String inventoryId, String itemId, String qty, String unitPrice, Button btn) {
        this.inventoryId = inventoryId;
        this.supId = itemId;
        this.quantity = qty;
        this.unitPrice = unitPrice;
        this.btn = btn;
    }
}
