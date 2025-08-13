package org.example.firstsemfptolayerd.controller;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.firstsemfptolayerd.BO.custom.PlantBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;

import java.sql.SQLException;

public class PlantDetailController {
    public ComboBox<String> cmbPlantId;
    public TextField txtUnitPrice;
    public TextField txtplantQty;

    private final PlantBO plantBO = (PlantBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.PLANT);

    public void loadPlantIds() {
        try {
            cmbPlantId.setItems(plantBO.getAllPlantIDS());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String getSelectedPlantId() {
        return cmbPlantId.getValue();
    }

    public String getQuantity() {
        return txtplantQty.getText();
    }

    public String getUnitPrice() {
        return txtUnitPrice.getText();
    }
}
