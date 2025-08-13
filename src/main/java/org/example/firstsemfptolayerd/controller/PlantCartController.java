package org.example.firstsemfptolayerd.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.firstsemfptolayerd.BO.custom.PlantBO;
import org.example.firstsemfptolayerd.BO.custom.PlantCartBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.entity.Plant;
import org.example.firstsemfptolayerd.model.CartDTO;
import org.example.firstsemfptolayerd.model.PlantDTO;

import java.sql.SQLException;

public class PlantCartController {
    public ComboBox<String> cmbPlantId;
    public Label lblPlantName;
    public Label lblUnitplantPrice;
    public TextField txtplantQty;
    private final PlantBO plantBO = (PlantBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.PLANT);
    private final PlantCartBO plantCartBO = (PlantCartBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.PLANTCART);

    public void initialize() throws Exception {
        loadPlantIds();
        txtplantQty.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            try {
                OrderPageController.plantQty = Integer.parseInt(txtplantQty.getText());
            } catch (Exception e) {
            }
        });

    }

    void loadPlantIds() {
        try {
            cmbPlantId.setItems(plantBO.getAllPlantIDS());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void btnSearchplantOnAction(ActionEvent actionEvent) {
        String plantId = cmbPlantId.getValue();

        if (plantId == null || plantId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please select a Plant ID.");
            return;
        }

        try {
            Plant plant = plantBO.searchPlantByName(plantId);
            CartDTO cart = plantCartBO.searchPlantUnitprice(plantId);

            OrderPageController.plantId = plantId;

            boolean foundSomething = false;

            if (plant != null) {
                lblPlantName.setText(plant.getName());
                System.out.println(plant.getName());
                foundSomething = true;
            } else {
                lblPlantName.setText("N/A");
            }


            if (cart != null) {
                lblUnitplantPrice.setText(cart.getUnitPrice().toString());
                foundSomething = true;
            } else {
                lblUnitplantPrice.setText("N/A");
            }

            if (foundSomething) {
                showAlert(Alert.AlertType.INFORMATION, "Plant details found.");
            } else {
                showAlert(Alert.AlertType.WARNING, "Plant not found.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
        }
    }
    public String getSelectedPlantId() {
        return cmbPlantId.getValue();
    }

    public String getPlantName() {
        return lblPlantName.getText();
    }

    public String getQuantity() {
        return txtplantQty.getText();
    }

    public String getUnitPrice() {
        return lblUnitplantPrice.getText();
    }

    private void showAlert(Alert.AlertType alertType, String plantFound) {
        new Alert(alertType, plantFound).show();
    }
}


