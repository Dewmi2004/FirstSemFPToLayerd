package org.example.firstsemfptolayerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.firstsemfptolayerd.BO.custom.EmployeeBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.model.EmployeeDTO;
import org.example.firstsemfptolayerd.view.tdm.EmployeeTM;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EmployeePageController {
    public TableColumn<?,?> clmEmail;
    public TableColumn<?,?> clmContact;
    public TableColumn<?,?> clmGender;
    public TableColumn<?,?> clmDob;
    public TableColumn<?,?> clmAddress;
    public TableColumn<?,?> clmName;
    public TableColumn<?,?> clmEmployeeId;
    public TableView<EmployeeTM> tblEmployee;
    public Button btnGReport;
    public Button btnReset;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    public TextField txtContact;
    public TextField txtEmail;
    public TextField txtGender;
    public DatePicker dpEmployee;
    public TextField txtAddress;
    public TextField txtName;
    public Label lblEmployeeid;
    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.EMPLOYEE);
    public void initialize() throws Exception {
        setCellValueFactory();
        setNextId();
        loadTable();
    }

    private void setCellValueFactory() {
        clmEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void setNextId() throws Exception {
        lblEmployeeid.setText(employeeBO.getNextEmployeeId());
    }

    private void loadTable() throws Exception {
        ObservableList<EmployeeTM> obc = FXCollections.observableArrayList();
        List<EmployeeDTO> list = employeeBO.getAllEmployees();
        list.sort(Comparator.comparing(EmployeeDTO::getId));
        for (EmployeeDTO e : list) {
            obc.add(new EmployeeTM(e.getId(),e.getName(),e.getAddress(),e.getDob(),e.getGender(),e.getContact(),e.getEmail()));
        }
        tblEmployee.setItems(obc);
    }

    private boolean isValidInput() {
        String nameRegex = "^[A-Za-z\\s]{3,}$";
        String addressRegex = "^[\\w\\s,.-]{5,}$";
        String genderRegex = "^(?i)(male|female|other)$";
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";
        String contactRegex = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";

        if (!txtName.getText().matches(nameRegex)) return showAlert("Invalid Name");
        if (!txtAddress.getText().matches(addressRegex)) return showAlert("Invalid Address");
        if (!txtGender.getText().matches(genderRegex)) return showAlert("Invalid Gender");
        if (dpEmployee.getValue() == null) return showAlert("Select valid DOB");
        if (!txtEmail.getText().matches(emailRegex)) return showAlert("Invalid Email");
        if (!txtContact.getText().matches(contactRegex)) return showAlert("Invalid Contact");
        return true;
    }

    private boolean showAlert(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).show();
        return false;
    }

    private EmployeeDTO getEmployeeData() {
        return new EmployeeDTO(
                lblEmployeeid.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtGender.getText(),
                String.valueOf(dpEmployee.getValue()),
                txtEmail.getText(),
                txtContact.getText()
        );
    }

    private void clearFields() throws Exception {
        txtName.clear();
        txtAddress.clear();
        txtGender.clear();
        dpEmployee.getEditor().clear();
        txtEmail.clear();
        txtContact.clear();
        setNextId();
    }

    public void btnGenarateROnAction(ActionEvent actionEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws Exception {
        clearFields();
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        tblEmployee.getSelectionModel().clearSelection();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        String id = lblEmployeeid.getText();
        if (id.isEmpty()) return;
        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Delete this Employee?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            if (employeeBO.deleteEmployee(id)) {
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
            } else new Alert(Alert.AlertType.ERROR, "Delete Failed").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        if (!isValidInput()) return;
        if (employeeBO.updateEmployee(getEmployeeData())) {
            loadTable();
            setNextId();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
        }
        else new Alert(Alert.AlertType.ERROR, "Update Failed").show();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        if (!isValidInput()) return;
        if (employeeBO.saveEmployee(getEmployeeData())) {
            loadTable(); setNextId(); clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Employee Saved").show();
        } else new Alert(Alert.AlertType.ERROR, "Save Failed").show();
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        EmployeeTM selected =  tblEmployee.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblEmployeeid.setText(selected.getId());
            txtName.setText(selected.getName());
            txtAddress.setText(selected.getAddress());
            txtGender.setText(selected.getGender());
            dpEmployee.setValue(LocalDate.parse(selected.getDob()));
            txtEmail.setText(selected.getEmail());
            txtContact.setText(selected.getContact());
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
