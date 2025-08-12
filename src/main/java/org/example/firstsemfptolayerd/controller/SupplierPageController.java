package org.example.firstsemfptolayerd.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.firstsemfptolayerd.BO.custom.SupplierBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.model.SupplierDTO;
import org.example.firstsemfptolayerd.view.tdm.SupplierTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class SupplierPageController {
    public Label lblSupplierId;
    public TextField txtName;
    public TextField txtContact;
    public TextField txtAddress;
    public ComboBox<String> CBoxSupplyType;
    public TextField txtEmail;
    public Button btnSave1;
    public Button btnUpdate1;
    public Button btnDelete1;
    public Button btnReset1;
    public TableView<SupplierTM> tblSupplier;
    public TableColumn<?,?> colSupplierId;
    public TableColumn<?,?> colName;
    public TableColumn<?,?> colContact;
    public TableColumn<?,?> colCompanyAddress;
    public TableColumn<?,?> colSupplyType;
    public TableColumn<?,?> colEmail;
    private final SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.SUPPLIER);
    public Button btnEmail1;

    public void initialize() throws Exception {
        setCellValueFactory();
        setNextId();
        loadTable();

        CBoxSupplyType.getItems().addAll("Fish", "Plants", "Equipment", "Food", "Other");
    }

    private void setCellValueFactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colCompanyAddress.setCellValueFactory(new PropertyValueFactory<>("companyAddress"));
        colSupplyType.setCellValueFactory(new PropertyValueFactory<>("supplyType"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void setNextId() throws Exception {
        lblSupplierId.setText(supplierBO.getNextSupplierId());
    }

    private void loadTable() throws Exception {
        tblSupplier.getItems().clear();

        try {
            ArrayList<SupplierDTO> allSuppliers = supplierBO.getAllSuppliers();
            for (SupplierDTO supplier : allSuppliers) {
                tblSupplier.getItems().add(new SupplierTM(
                        supplier.getSupId(),
                        supplier.getName(),
                        supplier.getContact(),
                        supplier.getCompanyAddress(),
                        supplier.getSupplyType(),
                        supplier.getEmail()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean validateInputs() {
        StringBuilder errors = new StringBuilder();
        String namePattern = "^[A-Za-z ]{2,50}$";
        String contactPattern = "^[0-9]{10}$";
        String addressPattern = "^[\\w\\s,.-]{5,100}$";
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$";

        if (!txtName.getText().matches(namePattern)) errors.append("Invalid name.\n");
        if (!txtContact.getText().matches(contactPattern)) errors.append("Invalid contact.\n");
        if (!txtAddress.getText().matches(addressPattern)) errors.append("Invalid address.\n");
        if (CBoxSupplyType.getValue() == null || CBoxSupplyType.getValue().toString().isEmpty()) errors.append("Supply type required.\n");
        if (!txtEmail.getText().matches(emailPattern)) errors.append("Invalid email.\n");

        if (!errors.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, errors.toString()).show();
            return false;
        }
        return true;
    }
     private SupplierDTO getSupplierData() {
        return new SupplierDTO(
                lblSupplierId.getText(),
                txtName.getText(),
                txtContact.getText(),
                txtAddress.getText(),
                (String) CBoxSupplyType.getValue(),
                txtEmail.getText()
        );
    }

    private void clearFields() throws Exception {
        txtName.clear();
        txtContact.clear();
        txtAddress.clear();
        CBoxSupplyType.getSelectionModel().clearSelection();
        txtEmail.clear();
        setNextId();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;
        boolean isSaved = supplierBO.saveSupplier(getSupplierData());
        if (isSaved) {
            loadTable();
            setNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Supplier Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Saved").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;
        boolean isUpdated = supplierBO.updateSupplier(getSupplierData());
        if (isUpdated) {
            loadTable();
            setNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Supplier Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Updated").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        String id = lblSupplierId.getText();
        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Select a supplier to delete").show();
            return;
        }
        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Delete this Supplier?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = supplierBO.deleteSupplier(id);
            if (isDeleted) {
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier Not Deleted").show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws Exception {
        clearFields();
        btnSave1.setDisable(false);
        btnUpdate1.setDisable(true);
        btnDelete1.setDisable(true);
        tblSupplier.getSelectionModel().clearSelection();
    }

    public void btnEmailOnAction(ActionEvent actionEvent) {
        org.example.firstsemfptolayerd.Dao.EmailUtil.sendSupplierWelcomeEmail(txtEmail.getText(),txtName.getText());
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        SupplierTM selectedItem =  tblSupplier.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblSupplierId.setText(selectedItem.getSupId());
            txtName.setText(selectedItem.getName());
            txtContact.setText(selectedItem.getContact());
            txtAddress.setText(selectedItem.getCompanyAddress());
            CBoxSupplyType.setValue(selectedItem.getSupplyType());
            txtEmail.setText(selectedItem.getEmail());

            btnSave1.setDisable(true);
            btnUpdate1.setDisable(false);
            btnDelete1.setDisable(false);
            btnEmail1.setDisable(false);
        }
    }
}
