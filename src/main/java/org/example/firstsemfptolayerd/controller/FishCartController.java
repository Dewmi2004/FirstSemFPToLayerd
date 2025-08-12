package org.example.firstsemfptolayerd.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.firstsemfptolayerd.BO.custom.FishBO;
import org.example.firstsemfptolayerd.BO.custom.FishCartBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.model.CartDTO;
import org.example.firstsemfptolayerd.model.FishDTO;

import java.sql.SQLException;

public class FishCartController {
    public ComboBox<String> cmbFishId;
    public Label lblName;
    public Label lblUnitePrice;
    public TextField txtQty;

private final FishCartBO fishCartBO =  (FishCartBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.FISHCART);
private final FishBO fishBO = (FishBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.FISH);

    public void initialize() throws Exception {
        loadFishIds();

        txtQty.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            try {
                OrderPageController.fishQty = Integer.parseInt(txtQty.getText());
            }catch (NumberFormatException e) {
            }
        });
    }

    public void loadFishIds() throws SQLException, ClassNotFoundException {
        cmbFishId.setItems(fishBO.getAllFishIDS());
    }
    public void btnSearchfishOnAction(ActionEvent actionEvent) {
        String fishId = cmbFishId.getValue();

        if (fishId == null || fishId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please select a Fish ID.");
            return;
        }

        try {
            FishDTO fish = fishBO.searchFishByName(fishId);
            CartDTO cart = fishCartBO.searchFishUnitPrice(fishId);

            OrderPageController.fishId = fishId;

            boolean foundSomething = false;

            if (fish != null) {
                lblName.setText(fish.getName());
                foundSomething = true;
            } else {
                lblName.setText("N/A");
            }

            if (cart != null) {
                lblUnitePrice.setText(cart.getUnitPrice().toString());
                foundSomething = true;
            } else {
                lblUnitePrice.setText("N/A");
            }

            if (foundSomething) {
                showAlert(Alert.AlertType.INFORMATION, "Fish details found.");
            } else {
                showAlert(Alert.AlertType.WARNING, "Fish not found.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
        }
    }


    private void showAlert(Alert.AlertType alertType, String fishFound) {
        new Alert(alertType, fishFound).show();
    }
    public String getSelectedFishId() {
        return cmbFishId.getValue();
    }

    public String getFishName() {
        return lblName.getText();
    }

    public String getQuantity() {
        return txtQty.getText();
    }

    public String getUnitPrice() {return lblUnitePrice.getText();}

}
