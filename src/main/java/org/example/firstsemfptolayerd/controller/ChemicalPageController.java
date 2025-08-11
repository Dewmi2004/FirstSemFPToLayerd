package org.example.firstsemfptolayerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.firstsemfptolayerd.BO.custom.ChemicalBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.model.ChemicalDTO;
import org.example.firstsemfptolayerd.view.tdm.ChemicalTM;

import java.util.ArrayList;
import java.util.Optional;

public class ChemicalPageController {
    public Label lblChemicalId;
    public ComboBox<String> cBoxType;
    public TextField txtConcentration;
    public TextField txtStoreType;
    public TextField txtquantity;
    public TextField txtName;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public Button btnGReport;
    public TableView<ChemicalTM> tblChemical;
    public TableColumn<?,?> clmid;
    public TableColumn<?,?> clmtype;
    public TableColumn<?,?> clmconcentration;
    public TableColumn<?,?> clmstoretype;
    public TableColumn<?,?> clmname;
    public TableColumn<?,?> clmQuntity;
    private final ChemicalBO chemicalBO = (ChemicalBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.CHEMICAL);

    public void initialize() throws Exception {
        setCellValueFactory();
        setNextId();
        loadComboData();
        loadTable();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private boolean validateInputs() {
        StringBuilder errorMsg = new StringBuilder();
        String namePattern = "^[A-Za-z ]{2,50}$";
        String concentrationPattern = "^[0-9]+(\\.[0-9]{1,2})?$";
        String quantityPattern = "^[0-9]{1,5}$";
        String storeTypePattern = "^[A-Za-z ]{3,50}$";

        if (cBoxType.getValue() == null || cBoxType.getValue().toString().isEmpty()) errorMsg.append("Chemical type is required.\n");
        if (txtConcentration.getText().isEmpty() || !txtConcentration.getText().matches(concentrationPattern)) errorMsg.append("Invalid concentration.\n");
        if (txtStoreType.getText().isEmpty() || !txtStoreType.getText().matches(storeTypePattern)) errorMsg.append("Invalid store type.\n");
        if (txtName.getText().isEmpty() || !txtName.getText().matches(namePattern)) errorMsg.append("Invalid chemical name.\n");
        if (txtquantity.getText().isEmpty() || !txtquantity.getText().matches(quantityPattern)) errorMsg.append("Invalid quantity.\n");

        if (errorMsg.length() > 0) {
            new Alert(Alert.AlertType.WARNING, errorMsg.toString()).show();
            return false;
        }
        return true;
    }

    private void setCellValueFactory() {
        clmid.setCellValueFactory(new PropertyValueFactory<>("chemicalId"));
        clmtype.setCellValueFactory(new PropertyValueFactory<>("acidOrBase"));
        clmconcentration.setCellValueFactory(new PropertyValueFactory<>("concentration"));
        clmstoretype.setCellValueFactory(new PropertyValueFactory<>("storeType"));
        clmname.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmQuntity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void loadComboData() {
        cBoxType.setItems(FXCollections.observableArrayList("Acid", "Base", "Neutral"));
    }

    private void loadTable() throws Exception {
        ArrayList<ChemicalDTO> list = chemicalBO.getAllChemicals();
        ObservableList<ChemicalTM> obList = FXCollections.observableArrayList();
        for (ChemicalDTO dto : list) {
            obList.add(new ChemicalTM(dto.getChemicalId(), dto.getAcidOrBase(), dto.getConcentration(), dto.getStoreType(), dto.getName(), dto.getQuantity()));
        }
        tblChemical.setItems(obList);
    }

    private void setNextId() throws Exception {
        lblChemicalId.setText(chemicalBO.getNextChemicalId());
    }
    public void ChemicalOnMouseClick(MouseEvent mouseEvent) {
        ChemicalTM selected =  tblChemical.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblChemicalId.setText(selected.getChemicalId());
            cBoxType.setValue(selected.getAcidOrBase());
            txtConcentration.setText(selected.getConcentration());
            txtStoreType.setText(selected.getStoreType());
            txtName.setText(selected.getName());
            txtquantity.setText(selected.getQuantity());
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void btnGenerateROnAction(ActionEvent actionEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws Exception {
        clearFields();
        tblChemical.getSelectionModel().clearSelection();
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void clearFields() throws Exception {
        cBoxType.getSelectionModel().clearSelection();
        txtConcentration.clear();
        txtStoreType.clear();
        txtName.clear();
        txtquantity.clear();
        setNextId();
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        String id = lblChemicalId.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this chemical?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            if (chemicalBO.deleteChemical(id)) {
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Chemical Deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Delete Chemical").show();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;
        ChemicalDTO dto = new ChemicalDTO(lblChemicalId.getText(), (String) cBoxType.getValue(), txtConcentration.getText(), txtStoreType.getText(), txtName.getText(), txtquantity.getText());
        if (chemicalBO.updateChemical(dto)) {
            loadTable();
            clearFields();
            setNextId();
            new Alert(Alert.AlertType.INFORMATION, "Chemical Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Update Chemical").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;
        ChemicalDTO dto = new ChemicalDTO(lblChemicalId.getText(), (String) cBoxType.getValue(), txtConcentration.getText(), txtStoreType.getText(), txtName.getText(), txtquantity.getText());
        if (chemicalBO.saveChemical(dto)) {
            loadTable();
            clearFields();
            setNextId();
            new Alert(Alert.AlertType.INFORMATION, "Chemical Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Save Chemical").show();
        }

    }
}
