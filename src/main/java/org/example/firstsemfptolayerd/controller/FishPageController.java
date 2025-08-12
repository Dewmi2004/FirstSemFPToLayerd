package org.example.firstsemfptolayerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.firstsemfptolayerd.BO.custom.FishBO;
import org.example.firstsemfptolayerd.BO.custom.TankBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.impl.FishDaoImpl;
import org.example.firstsemfptolayerd.model.FishDTO;
import org.example.firstsemfptolayerd.view.tdm.FishTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class FishPageController{

    public Label lblFishId;
    public TextField txtName;
    public ComboBox<String> cmbSize;
    public ComboBox<String>  cmbTankId;
    public ComboBox<String>  cmbGender;
    public ComboBox<String>  cmbWaterType;
    public ComboBox<String>  cmbCountry;
    public TextField txtColor;
    public TextField txtQuantity;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView<FishTM> tblFish;
    public TableColumn<?,?> clmFishId;
    public TableColumn<?,?> clmName;
    public TableColumn<?,?> clmSize;
    public TableColumn<?,?> clmTankId;
    public TableColumn<?,?> clmGender;
    public TableColumn<?,?> clmWaterType;
    public TableColumn<?,?> clmCountry;
    public TableColumn<?,?> clmColor;
    public TableColumn<?,?> clmQuantity;
    private final FishBO fishBO = (FishBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.FISH);
    private final FishDaoImpl fishDaoImpl = (FishDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.FISH);
    private final TankBO tankBO = (TankBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.TANK);

    public void initialize() throws Exception {
        setCellValueFactory();
        setNextId();
        ComboDataSet();
        loadTable();
    }

    private boolean isValidInput() {
        String nameRegex = "^[A-Za-z\\s]{3,}$";
        String colorRegex = "^[A-Za-z\\s]{3,}$";
        String quantityRegex = "^\\d+$";
        String genderRegex = "^(?i)(male|female|other)$";

        if (!txtName.getText().matches(nameRegex)) {
            showAlert("Invalid Name! Only letters and spaces, minimum 3 characters.");
            return false;
        }
        if (cmbSize.getValue() == null) {
            showAlert("Please select a valid Fish Size.");
            return false;
        }
        if (cmbTankId.getValue() == null) {
            showAlert("Please select a valid Tank ID.");
            return false;
        }
        if (cmbGender.getValue() == null || !cmbGender.getValue().matches(genderRegex)) {
            showAlert("Invalid Gender! Use Male, Female, or Other.");
            return false;
        }
        if (cmbWaterType.getValue() == null) {
            showAlert("Please select a valid Water Type.");
            return false;
        }
        if (cmbCountry.getValue() == null) {
            showAlert("Please select a valid Country.");
            return false;
        }
        if (!txtColor.getText().matches(colorRegex)) {
            showAlert("Invalid Color! Only letters and spaces, minimum 3 characters.");
            return false;
        }
        if (!txtQuantity.getText().matches(quantityRegex)) {
            showAlert("Invalid Quantity! Must be a number.");
            return false;
        }
        return true;
    }

    private void showAlert(String message) {
        new Alert(Alert.AlertType.WARNING, message).show();
    }

    private void ComboDataSet() throws Exception {
        cmbSize.setItems(fishDaoImpl.getFishSize());
         cmbTankId.setItems(tankBO.getTankIds());
        cmbGender.setItems(fishDaoImpl.getFishGender());
        cmbWaterType.setItems(fishDaoImpl.getFishWatertype());
        cmbCountry.setItems(fishDaoImpl.getFishCountry());
    }

    private void setCellValueFactory() {
        clmFishId.setCellValueFactory(new PropertyValueFactory<>("fishId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        clmTankId.setCellValueFactory(new PropertyValueFactory<>("tankId"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmWaterType.setCellValueFactory(new PropertyValueFactory<>("waterType"));
        clmCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        clmColor.setCellValueFactory(new PropertyValueFactory<>("colour"));
        clmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void loadTable() throws Exception {
        ArrayList<FishDTO> fishList = fishBO.getAllFish();
        ObservableList<FishTM> obList = FXCollections.observableArrayList();
        for (FishDTO fish : fishList) {
            obList.add(new FishTM(
                    fish.getFishId(),
                    fish.getName(),
                    fish.getSize(),
                    fish.getTankId(),
                    fish.getGender(),
                    fish.getWaterType(),
                    fish.getCountry(),
                    fish.getColour(),
                    fish.getQuantity()
            ));
        }
        tblFish.setItems(obList);
    }

    private void setNextId() throws Exception {
        lblFishId.setText(fishBO.getNextFishId());
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        if (!isValidInput()) return;

        FishDTO dto = new FishDTO(
                lblFishId.getText(),
                txtName.getText(),
                cmbSize.getValue(),
                cmbTankId.getValue(),
                cmbGender.getValue(),
                cmbWaterType.getValue(),
                cmbCountry.getValue(),
                txtColor.getText(),
                txtQuantity.getText()
        );

        if (fishBO.saveFish(dto)) {
            loadTable();
            setNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Fish Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fish Not Saved").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        if (!isValidInput()) return;

        FishDTO dto = new FishDTO(
                lblFishId.getText(),
                txtName.getText(),
                cmbSize.getValue(),
                cmbTankId.getValue(),
                cmbGender.getValue(),
                cmbWaterType.getValue(),
                cmbCountry.getValue(),
                txtColor.getText(),
                txtQuantity.getText()
        );

        if (fishBO.updateFish(dto)) {
            loadTable();
            setNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Fish Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fish Not Updated").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        String id = lblFishId.getText();

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a Fish to delete").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Fish?");
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(no, yes);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yes) {
            if (fishBO.deleteFish(id)) {
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Fish Deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fish Not Deleted").show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws Exception {
        clearFields();
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        tblFish.getSelectionModel().clearSelection();
    }


    public void clickOnAction(MouseEvent mouseEvent) {
        FishTM selectedItem = tblFish.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblFishId.setText(selectedItem.getFishId());
            txtName.setText(selectedItem.getName());
            cmbSize.setValue(selectedItem.getSize());
            cmbTankId.setValue(selectedItem.getTankId());
            cmbGender.setValue(selectedItem.getGender());
            cmbWaterType.setValue(selectedItem.getWaterType());
            cmbCountry.setValue(selectedItem.getCountry());
            txtColor.setText(selectedItem.getColour());
            txtQuantity.setText(selectedItem.getQuantity());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    private void clearFields() throws Exception {
        txtName.clear();
        cmbSize.getSelectionModel().clearSelection();
        cmbTankId.getSelectionModel().clearSelection();
        cmbGender.getSelectionModel().clearSelection();
        cmbWaterType.getSelectionModel().clearSelection();
        cmbCountry.getSelectionModel().clearSelection();
        txtColor.clear();
        txtQuantity.clear();
        setNextId();
    }

}
