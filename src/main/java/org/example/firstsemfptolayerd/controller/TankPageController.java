package org.example.firstsemfptolayerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.firstsemfptolayerd.BO.custom.TankBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.model.TankDTO;
import org.example.firstsemfptolayerd.view.tdm.TankTM;

import java.util.ArrayList;
import java.util.Optional;

public class TankPageController {
    public Label lblTankId;
    public ComboBox<String> CBoxGlass;
    public ComboBox<String> CBoxTank;
    public ComboBox<String> CBoxWater;
    public Button btnSave1;
    public Button btnUpdate1;
    public Button btnDelete1;
    public Button btnReset1;
    public Button btnGenarateR1;
    public TableView<TankTM> tblTank;
    public TableColumn<?,?> colTankId;
    public TableColumn<?,?> colGlassType;
    public TableColumn<?,?> colTankType;
    public TableColumn<?,?> colWaterType;
    private final TankBO tankBO = (TankBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.TANK);

    public void initialize() throws Exception {
        setCellValueFactory();
        setNextId();
        loadComboData();
        loadTable();
    }

    private void loadComboData() {
        CBoxGlass.setItems(tankBO.getGlassTypes());
        CBoxTank.setItems(tankBO.getTankTypes());
        CBoxWater.setItems(tankBO.getWaterTypes());
    }

    private void setCellValueFactory() {
        colTankId.setCellValueFactory(new PropertyValueFactory<>("tankId"));
        colGlassType.setCellValueFactory(new PropertyValueFactory<>("glassType"));
        colTankType.setCellValueFactory(new PropertyValueFactory<>("fishOrPlant"));
        colWaterType.setCellValueFactory(new PropertyValueFactory<>("waterType"));
    }

    private void loadTable() throws Exception {
        ArrayList<TankDTO> list = tankBO.getAllTanks();
        ObservableList<TankTM> obList = FXCollections.observableArrayList();
        for (TankDTO dto : list) {
            obList.add(new TankTM(dto.getTankId(), dto.getGlassType(), dto.getFishOrPlant(), dto.getWaterType()));
        }
        tblTank.setItems(obList);
    }

    private void setNextId() throws Exception {
        lblTankId.setText(tankBO.getNextTankId());
    }

    private boolean validateInputs() {
        String regex = "^[A-Za-z ]{3,30}$";
        if (CBoxGlass.getValue() == null || !CBoxGlass.getValue().matches(regex)) {
            showAlert("Invalid Glass Type. Use 3–30 letters only.");
            return false;
        }
        if (CBoxTank.getValue() == null || !CBoxTank.getValue().matches(regex)) {
            showAlert("Invalid Tank Type. Use 3–30 letters only.");
            return false;
        }
        if (CBoxWater.getValue() == null || !CBoxWater.getValue().matches("^[A-Za-z ]{3,50}$")) {
            showAlert("Invalid Water Type. Use 3–50 letters only.");
            return false;
        }
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;
        TankDTO dto = new TankDTO(lblTankId.getText(), CBoxGlass.getValue(), CBoxTank.getValue(), CBoxWater.getValue());
        if (tankBO.saveTank(dto)) {
            loadTable();
            setNextId();
            new Alert(Alert.AlertType.INFORMATION, "Tank Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Save Tank").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;
        TankDTO dto = new TankDTO(lblTankId.getText(), CBoxGlass.getValue(), CBoxTank.getValue(), CBoxWater.getValue());
        if (tankBO.updateTank(dto)) {
            loadTable();
            setNextId();
            new Alert(Alert.AlertType.INFORMATION, "Tank Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Update Tank").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        String id = lblTankId.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure You Want To Delete Tank ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            if (tankBO.deleteTank(id)) {
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Tank Deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Tank Not Deleted").show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws Exception {
        clearFields();
        btnSave1.setDisable(false);
        btnUpdate1.setDisable(true);
        btnDelete1.setDisable(true);
        tblTank.getSelectionModel().clearSelection();
}

    private void clearFields() throws Exception {
         CBoxGlass.getSelectionModel().clearSelection();
         CBoxTank.getSelectionModel().clearSelection();
         CBoxWater.getSelectionModel().clearSelection();
         setNextId();
    }

    public void btnGenarateROnAction(ActionEvent actionEvent) {
    }

    public void btnclickOnAction(MouseEvent mouseEvent) {
        TankTM selected = tblTank.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblTankId.setText(selected.getTankId());
            CBoxGlass.setValue(selected.getGlassType());
            CBoxTank.setValue(selected.getFishOrPlant());
            CBoxWater.setValue(selected.getWaterType());
            btnSave1.setDisable(true);
            btnUpdate1.setDisable(false);
            btnDelete1.setDisable(false);
        }
    }
}
