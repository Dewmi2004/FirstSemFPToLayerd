package org.example.firstsemfptolayerd.view.tdm;
import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class CartTM {
     private String ItemId;
     private String Name;
     private String Quantity;
     private String UnitPrice;
     private String Total;
     private Button btn ;
     private String fishId;
     private String plantId;
     private String totalQuantity;

     public CartTM(String itemId, String name, String quantity, String unitPrice, String total) {
          this.ItemId = itemId;
          this.Name = name;
          this.Quantity = quantity;
          this.UnitPrice = unitPrice;
          this.Total = total;

     }

    public CartTM(String unitPrice) {
          this.UnitPrice = unitPrice;
    }

    public CartTM(String itemId, String name, String qtyStr, String unitPriceStr, String s, Button btnRemove) {
         this.ItemId = itemId;
         this.Name = name;
         this.Quantity = qtyStr;
         this.UnitPrice = unitPriceStr;
         this.Total = s;
         this.btn = btnRemove;
    }
}
