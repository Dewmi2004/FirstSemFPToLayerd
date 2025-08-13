package org.example.firstsemfptolayerd.model;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QueryDTO {
    private Button btn;
    private String quantity;
    private String unitPrice;
    private String itemId;
    private String fishId;
    private String foodId;
    private String chemicalId;
    private String plantId;
}
