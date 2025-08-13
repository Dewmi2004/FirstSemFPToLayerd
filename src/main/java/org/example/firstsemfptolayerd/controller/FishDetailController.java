package org.example.firstsemfptolayerd.controller;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.firstsemfptolayerd.BO.custom.FishBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;

import java.sql.SQLException;

public class FishDetailController {
    public ComboBox<String> cmbfishId;
    public TextField txtUnitPrice;
    public TextField txtFishQty;

    FishBO fishBO = (FishBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.FISH);

    public void loadFishIds() {
        try {
            cmbfishId.setItems(fishBO.getAllFishIDS());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String getSelectedFishId() {
        return cmbfishId.getValue();
    }

    public String getQuantity() {
        return txtUnitPrice.getText();
    }

    public String getUnitPrice() {
        return txtUnitPrice.getText();
    }

}
