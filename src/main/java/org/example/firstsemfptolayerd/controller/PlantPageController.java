package org.example.firstsemfptolayerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.firstsemfptolayerd.BO.custom.PlantBO;
import org.example.firstsemfptolayerd.BO.custom.TankBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.model.PlantDTO;
import org.example.firstsemfptolayerd.view.tdm.PlantTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class PlantPageController {
    public Label lblPlantId;
    public TextField txtName;
    public ComboBox<String> CBoxType;
    public ComboBox<String> CBoxTank;
    public ComboBox<String> CBoxSize;
    public TextField txtQuantity;
    public Button btnSave1;
    public Button btnUpdate1;
    public Button btnDelete1;
    public Button btnReset1;
    public TableView<PlantTM> tblPlant;
    public TableColumn<?,?> colplantId;
    public TableColumn<?,?> colname;
    public TableColumn<?,?> colwaterrtype;
    public TableColumn<?,?> coltankId;
    public TableColumn<?,?> colsize;
    public TableColumn<?,?> colQuantity;
    private final PlantBO plantBO = (PlantBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.PLANT);
    private final TankBO tankBO = (TankBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.TANK);
    public void initialize() throws Exception {
        setCellValueFactory();
        setNextId();
        loadComboData();
        loadTable();
    }

    private void setCellValueFactory() {
        colplantId.setCellValueFactory(new PropertyValueFactory<>("plantId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colwaterrtype.setCellValueFactory(new PropertyValueFactory<>("waterType"));
        coltankId.setCellValueFactory(new PropertyValueFactory<>("tankId"));
        colsize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void loadComboData() throws Exception {
        CBoxType.setItems(FXCollections.observableArrayList("Freshwater", "Saltwater", "Brackish"));
        CBoxSize.setItems(FXCollections.observableArrayList("Small", "Medium", "Large"));
        CBoxTank.setItems(tankBO.getTankIds());
    }

    private void loadTable() throws Exception {
        ArrayList<PlantDTO> plants = plantBO.getAllPlants();
        ObservableList<PlantTM> obList = FXCollections.observableArrayList();

        for (PlantDTO dto : plants) {
            obList.add(new PlantTM(
                    dto.getPlantId(),
                    dto.getName(),
                    dto.getWaterType(),
                    dto.getTankId(),
                    dto.getSize(),
                    dto.getQuantity()
            ));
        }
        tblPlant.setItems(obList);
    }

    private void setNextId() throws Exception {
        lblPlantId.setText(plantBO.getNextPlantId());
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;

        PlantDTO dto = new PlantDTO(
                lblPlantId.getText(),
                txtName.getText(),
                 CBoxType.getValue(),
                CBoxTank.getValue(),
                CBoxSize.getValue(),
                txtQuantity.getText()
        );

        if (plantBO.savePlant(dto)) {
            loadTable();
            setNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Plant Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Plant Not Saved").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;

        PlantDTO dto = new PlantDTO(
                lblPlantId.getText(),
                txtName.getText(),
                CBoxType.getValue(),
                CBoxTank.getValue(),
                CBoxSize.getValue(),
                txtQuantity.getText()
        );

        if (plantBO.updatePlant(dto)) {
            loadTable();
            setNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Plant Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Plant Not Updated").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        String id = lblPlantId.getText();
        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Select a plant to delete").show();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete this plant?");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (plantBO.deletePlant(id)) {
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Plant Deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Plant Not Deleted").show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws Exception {
        clearFields();
        btnSave1.setDisable(false);
        btnUpdate1.setDisable(true);
        btnDelete1.setDisable(true);
        tblPlant.getSelectionModel().clearSelection();
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        PlantTM selected = tblPlant.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblPlantId.setText(selected.getPlantId());
            txtName.setText(selected.getName());
            CBoxType.setValue(selected.getWaterType());
            CBoxTank.setValue(selected.getTankId());
            CBoxSize.setValue(selected.getSize());
            txtQuantity.setText(selected.getQuantity());
            btnSave1.setDisable(true);
            btnUpdate1.setDisable(false);
            btnDelete1.setDisable(false);
        }
    }
    private void clearFields() throws Exception {
        txtName.clear();
        CBoxType.getSelectionModel().clearSelection();
        CBoxTank.getSelectionModel().clearSelection();
        CBoxSize.getSelectionModel().clearSelection();
        txtQuantity.clear();
        setNextId();
    }

    private boolean validateInputs() {
        String nameRegex = "^[A-Za-z ]{2,30}$";
        String quantityRegex = "^[1-9][0-9]*$";

        if (!txtName.getText().matches(nameRegex)) {
            showAlert("Invalid Name. Use 2–30 letters only.");
            return false;
        }
        if (CBoxType.getValue() == null) {
            showAlert("Please select a Water Type.");
            return false;
        }
        if (CBoxTank.getValue() == null) {
            showAlert("Please select a Tank.");
            return false;
        }
        if (CBoxSize.getValue() == null) {
            showAlert("Please select a Size.");
            return false;
        }
        if (!txtQuantity.getText().matches(quantityRegex)) {
            showAlert("Invalid Quantity. Use positive whole numbers only.");
            return false;
        }
        return true;
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).show();
    }
}
