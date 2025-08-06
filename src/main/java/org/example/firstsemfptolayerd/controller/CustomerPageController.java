package org.example.firstsemfptolayerd.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.firstsemfptolayerd.BO.custom.CustomerBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.model.CustomerDTO;
import org.example.firstsemfptolayerd.view.tdm.CustomerTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerPageController {
    public Label lblCusId;
    public TextField txtName;
    public TextField txtAddress;
    public DatePicker dpCustomer;
    public TextField txtGender;
    public TextField txtContact;
    public TextField txtEmail;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public Button btnGReport;
    public TableView <CustomerTM>tblCustomer;
    public TableColumn<?,?> clmCusId;
    public TableColumn<?,?> clmName;
    public TableColumn<?,?> clmAddress;
    public TableColumn<?,?> clmDob;
    public TableColumn<?,?> clmGender;
    public TableColumn<?,?> clmContact;
    public TableColumn<?,?> clmEmail;
    private final CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.CUSTOMER);
    public void initialize() throws Exception {
        setCellValueFactory();
        setNextId();
        loadTable();

    }

    private void setCellValueFactory() {
        clmCusId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private void setNextId() throws Exception {
        lblCusId.setText(customerBO.getNextCustomerId());
    }

    private void loadTable() throws Exception {
        tblCustomer.getItems().clear();

        try {
            ArrayList<CustomerDTO> allcustomer =  customerBO.getAllCustomers();
            for (CustomerDTO customer : allcustomer) {
                tblCustomer.getItems().add(new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getGender(),
                        customer.getDob(),
                        customer.getEmail(),
                        customer.getContact()
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private boolean validateInputs() {
        StringBuilder errors = new StringBuilder();
        String namePattern = "^[A-Za-z ]{2,50}$";
        String addressPattern = "^[\\w\\s,.-]{5,100}$";
        String genderPattern = "^(?i)(Male|Female|Other)$";
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$";
        String contactPattern = "^[0-9]{10}$";

        if (!txtName.getText().matches(namePattern)) errors.append("Invalid name.\n");
        if (!txtAddress.getText().matches(addressPattern)) errors.append("Invalid address.\n");
        if (!txtGender.getText().matches(genderPattern)) errors.append("Invalid gender.\n");
        if (dpCustomer.getValue() == null) errors.append("Date of birth required.\n");
        if (!txtEmail.getText().matches(emailPattern)) errors.append("Invalid email.\n");
        if (!txtContact.getText().matches(contactPattern)) errors.append("Invalid contact.\n");

        if (!errors.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, errors.toString()).show();
            return false;
        }
        return true;
    }

    private CustomerDTO getCustomerData() {
        return new CustomerDTO(
                lblCusId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtGender.getText(),
                String.valueOf(dpCustomer.getValue()),
                txtEmail.getText(),
                txtContact.getText()
        );
    }

    private void clearFields() throws Exception {
        txtName.clear(); txtAddress.clear(); txtGender.clear();
        dpCustomer.getEditor().clear(); txtEmail.clear(); txtContact.clear();
        setNextId();
    }
    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;
        boolean isSaved = customerBO.saveCustomer(getCustomerData());
        if (isSaved) {
            loadTable(); setNextId(); clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Customer Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer Not Saved").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        if (!validateInputs()) return;
        boolean isUpdated = customerBO.updateCustomer(getCustomerData());
        if (isUpdated) {
            loadTable(); setNextId(); clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Customer Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer Not Updated").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        String id = lblCusId.getText();
        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Select a customer to delete").show(); return;
        }
        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Delete this Customer?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = customerBO.deleteCustomer(id);
            if (isDeleted) {
                loadTable(); clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer Not Deleted").show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws Exception {
        clearFields();
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        tblCustomer.getSelectionModel().clearSelection();
    }

    public void btnGenarateROnAction(ActionEvent actionEvent) {

    }

    public void clickOnAction(MouseEvent mouseEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblCusId.setText(selectedItem.getId());
            txtName.setText(selectedItem.getName());
            txtAddress.setText(selectedItem.getAddress());
            txtGender.setText(selectedItem.getGender());
            dpCustomer.setValue(LocalDate.parse(selectedItem.getDob()));
            txtEmail.setText(selectedItem.getEmail());
            txtContact.setText(selectedItem.getContact());

            // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    }

