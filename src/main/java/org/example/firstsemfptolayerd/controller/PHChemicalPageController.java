package org.example.firstsemfptolayerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.firstsemfptolayerd.BO.custom.ChemicalBO;
import org.example.firstsemfptolayerd.BO.custom.PHChemicalBO;
import org.example.firstsemfptolayerd.BO.custom.TankBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.model.PHChemicalDTO;
import org.example.firstsemfptolayerd.view.tdm.PHChemicalTM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


public class PHChemicalPageController {

    private final PHChemicalBO phChemicalBO = (PHChemicalBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.PH_CHEMICAL);
    private final TankBO tankBO = (TankBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.TANK);
    private final ChemicalBO chemicalBO = (ChemicalBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.CHEMICAL);
    public ComboBox<String> cmbTankId;
    public ComboBox<String> cmbChemicalId;
    public ComboBox<String> cmbPhLevel;
    public DatePicker datePickerdate;
    public TextField txtTime;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public Button btnGReport;
    public TableView<PHChemicalTM> tblPHChemical;
    public TableColumn<?,?> clmTankId;
    public TableColumn<?,?> clmChemicalId;
    public TableColumn<?,?> clmPHLevel;
    public TableColumn<?,?> clmDate;
    public TableColumn<?,?> clmTime;

    public void initialize() throws Exception {
        setCellValueFactory();
        loadComboData();
        loadTable();
    }

    private boolean validateInputs() {
        String timeRegex = "^([01]\\d|2[0-3]):[0-5]\\d$";
        if (cmbTankId.getValue() == null) return showWarn("Select a Tank ID.");
        if (cmbChemicalId.getValue() == null) return showWarn("Select a Chemical ID.");
        if (cmbPhLevel.getValue() == null) return showWarn("Select a PH Level.");
        if (datePickerdate.getValue() == null) return showWarn("Select a date.");
        if (!txtTime.getText().matches(timeRegex)) return showWarn("Invalid time format (HH:mm).");
        return true;
    }
    private boolean showWarn(String msg) { new Alert(Alert.AlertType.WARNING, msg).show(); return false; }

    private void setCellValueFactory() {
        clmTankId.setCellValueFactory(new PropertyValueFactory<>("tankId"));
        clmChemicalId.setCellValueFactory(new PropertyValueFactory<>("chemicalId"));
        clmPHLevel.setCellValueFactory(new PropertyValueFactory<>("phLevel"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    private void loadComboData() throws Exception {
       cmbTankId.setItems(tankBO.getTankIds());
        cmbChemicalId.setItems(chemicalBO.getAllChemicalIDS());
        cmbPhLevel.setItems(FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12"));
    }

    private void loadTable() throws Exception {
        ArrayList<PHChemicalDTO> list = phChemicalBO.getAllPHChemicals();
        ObservableList<PHChemicalTM> obList = FXCollections.observableArrayList();
        for (PHChemicalDTO dto : list) {
            obList.add(new PHChemicalTM(dto.getTankId(), dto.getChemicalId(), dto.getPhLevel(), dto.getDate(), dto.getTime()));
        }
        tblPHChemical.setItems(obList);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {if (!validateInputs()) return;
        boolean isSaved = phChemicalBO.savePHChemical(getFormData());
        if (isSaved) {
            loadTable();
            resetForm();
            info("Saved!");
        } else error("Failed to save");
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {if (!validateInputs()) return;
        boolean isUpdated = phChemicalBO.updatePHChemical(getFormData());
        if (isUpdated) {
            loadTable();
            resetForm();
            info("Updated!");
        } else error("Failed to update");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        String date = String.valueOf(datePickerdate.getValue());
        Optional<ButtonType> confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (confirm.isPresent() && confirm.get() == ButtonType.YES) {
            if (phChemicalBO.deletePHChemical(date)) {
                loadTable();
                resetForm();
                info("Deleted!");
            } else error("Failed to delete");
        }
    }
    private PHChemicalDTO getFormData() {
        return new PHChemicalDTO(
                cmbTankId.getValue(),
                cmbChemicalId.getValue(),
                cmbPhLevel.getValue(),
                datePickerdate.getValue().toString(),
                txtTime.getText()
        );
    }

    private void resetForm() {
        cmbChemicalId.getSelectionModel().clearSelection();
        cmbTankId.getSelectionModel().clearSelection();
        cmbPhLevel.getSelectionModel().clearSelection();
        datePickerdate.setValue(LocalDate.now());
        txtTime.clear();
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetForm();
    }

    public void btnGenerateROnAction(ActionEvent actionEvent) {
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        PHChemicalTM selected = tblPHChemical.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cmbTankId.setValue(selected.getTankId());
            cmbChemicalId.setValue(selected.getChemicalId());
            cmbPhLevel.setValue(selected.getPhLevel());
            datePickerdate.setValue(LocalDate.parse(selected.getDate()));
            txtTime.setText(selected.getTime());
        }
    }
    private void info(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
    private void error(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
    }

}
