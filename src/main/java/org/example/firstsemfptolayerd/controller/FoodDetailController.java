package org.example.firstsemfptolayerd.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.firstsemfptolayerd.BO.custom.FoodBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;

import java.sql.SQLException;

public class FoodDetailController {
    public ComboBox<String> cmbfoodId;
    public TextField txtUnitPrice;
    public TextField txtfoodQty;

    private final FoodBO foodBO = (FoodBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.FOOD);

    public void loadFoodIds() {
        try {
            ObservableList<String> foodIds = (ObservableList<String>) foodBO.getAllFoodIDS();
            if (foodIds != null && !foodIds.isEmpty()) {
                cmbfoodId.setItems(foodIds);
            } else {
                showAlert(Alert.AlertType.WARNING, "No chemical IDs found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Failed to load chemical IDs: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String s) {
        new Alert(alertType, s).show();
    }
    public String getSelectedFoodId() {
        return cmbfoodId.getValue();
    }

    public String getQuantity() {
        return txtUnitPrice.getText();
    }

    public String getUnitPrice() {
        return txtUnitPrice.getText();
    }


}
