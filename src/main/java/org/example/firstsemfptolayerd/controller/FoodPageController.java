package org.example.firstsemfptolayerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.firstsemfptolayerd.BO.custom.FoodBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.model.FoodDTO;
import org.example.firstsemfptolayerd.view.tdm.FoodTM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

public class FoodPageController {

    public Label lblFoodId;
    public TextField txtName;
    public TextField txtFishType;
    public DatePicker DPExDate;
    public TextField txtQuantity;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView<FoodTM> tblFood;
    public TableColumn<?,?> clmFoodId;
    public TableColumn<?,?> clmName;
    public TableColumn<?,?> clmFishType;
    public TableColumn<?,?> dpExDate;
    public TableColumn<?,?> clmQuantity;

    private final FoodBO foodBO = (FoodBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.FOOD);

    public void initialize() throws Exception {
        setCellValueFactory();
        setNextId();
        loadTable();
    }

    private boolean validateInputs() {
        String nameRegex = "^[A-Za-z ]{2,30}$";
        String fishTypeRegex = "^[A-Za-z ]{2,20}$";
        String quantityRegex = "^[1-9][0-9]*$";

        if (!txtName.getText().matches(nameRegex)) {
            showAlert("Invalid Name. Use 2–30 letters only.");
            return false;
        }
        if (!txtFishType.getText().matches(fishTypeRegex)) {
            showAlert("Invalid Fish Type. Use 2–20 letters only.");
            return false;
        }
        if (DPExDate.getValue() == null) {
            showAlert("Please select an expiry date.");
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

    private void setCellValueFactory() {
        clmFoodId.setCellValueFactory(new PropertyValueFactory<>("foodId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmFishType.setCellValueFactory(new PropertyValueFactory<>("fishType"));
        dpExDate.setCellValueFactory(new PropertyValueFactory<>("exDate"));
        clmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void loadTable() throws Exception {
        ArrayList<FoodDTO> list = foodBO.getAllFoods();
        ObservableList<FoodTM> obList = FXCollections.observableArrayList();
        for (FoodDTO dto : list) {
            obList.add(new FoodTM(dto.getFoodId(), dto.getName(), dto.getFishType(), dto.getExDate(), dto.getQuantity()));
        }
        tblFood.setItems(obList);
    }

    private void setNextId() throws Exception {
        lblFoodId.setText(foodBO.getNextFoodId());
    }
    public void btnClickOnACtion(MouseEvent mouseEvent) throws Exception {
        FoodTM selected = tblFood.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblFoodId.setText(selected.getFoodId());
            txtName.setText(selected.getName());
            txtFishType.setText(selected.getFishType());
            DPExDate.setValue(selected.getExDate().toLocalDate());
            txtQuantity.setText(String.valueOf(selected.getQuantity()));

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws Exception {
        clearFields();
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        tblFood.getSelectionModel().clearSelection();
    }

    private void clearFields() throws Exception {
        txtName.clear();
        txtFishType.clear();
        DPExDate.setValue(null);
        txtQuantity.clear();
        setNextId();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        String id = lblFoodId.getText();
        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a Food to delete").show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure You Want To Delete Food?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            if (foodBO.deleteFood(id)) {
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Food Deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Food Not Deleted").show();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;

        FoodDTO dto = new FoodDTO(
                lblFoodId.getText(),
                txtName.getText(),
                txtFishType.getText(),
                Date.valueOf(DPExDate.getValue()),
                txtQuantity.getText()
        );

        if (foodBO.updateFood(dto)) {
            loadTable();
            setNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Food Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Update").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;
        FoodDTO dto = new FoodDTO(
                lblFoodId.getText(),
                txtName.getText(),
                txtFishType.getText(),
                Date.valueOf(DPExDate.getValue()),
                txtQuantity.getText()
        );

        if (foodBO.saveFood(dto)) {
            loadTable();
            setNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Food Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Save").show();
        }
    }
}
