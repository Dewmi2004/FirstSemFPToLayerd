package org.example.firstsemfptolayerd.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.firstsemfptolayerd.BO.custom.ChemicalBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;

import java.sql.SQLException;

public class ChemicalDetailController {
    public ComboBox<String> cmbchemicalId;
    public TextField txtUnitPrice;
    public TextField txtchemicalQty;

    ChemicalBO chemicalBO = (ChemicalBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.CHEMICAL);

    public void loadChemicalIds() {
        try {
            ObservableList<String> chemicalIds = (ObservableList<String>) chemicalBO.getAllChemicalIDS();
            if (chemicalIds != null && !chemicalIds.isEmpty()) {
                cmbchemicalId.setItems(chemicalIds);
            } else {
                showAlert(Alert.AlertType.WARNING, "No chemical IDs found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Failed to load chemical IDs: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String getSelectedChemicalId() {
        return cmbchemicalId.getValue();
    }

    public String getQuantity() {
        return txtchemicalQty.getText();
    }

    public String getUnitPrice() {
        return txtUnitPrice.getText();
    }

    private void showAlert(Alert.AlertType alertType, String s) {
        new Alert(alertType, s).show();
    }

}
