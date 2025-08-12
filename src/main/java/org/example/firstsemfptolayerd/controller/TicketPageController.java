package org.example.firstsemfptolayerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.firstsemfptolayerd.BO.custom.CustomerBO;
import org.example.firstsemfptolayerd.BO.custom.EmployeeBO;
import org.example.firstsemfptolayerd.BO.custom.TicketBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.model.CustomerDTO;
import org.example.firstsemfptolayerd.model.TicketDTO;
import org.example.firstsemfptolayerd.view.tdm.TicketTM;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;

public class TicketPageController {
    public Label lblTicketId;
    public TextField txtAge;
    public TextField txtPrice;
    public DatePicker dpDate;
    public TextField txtTime;
    public TextField txtCustomerPhone;
    public Button btnSearchCustomer;
    public Label lblCustomerId;
    public Label lblCustomerInfo;
    public ComboBox<String> cmbEmployeeId;
    public TextField txtQuantity;
    public Button btnSave1;
    public Button btnUpdate1;
    public Button btnDelete1;
    public Button btnReset1;
    public TableView<TicketTM> tblTicket;
    public TableColumn<?,?> colTicketId;
    public TableColumn<?,?> colAge;
    public TableColumn<?,?> colPrice;
    public TableColumn<?,?> colDate;
    public TableColumn<?,?> colTime;
    public TableColumn<?,?> colCustomerId;
    public TableColumn<?,?> colEmployeeId;
    public TableColumn<?,?> colQuantity;
    public TableColumn<?,?> colFullPrice;
    private final TicketBO ticketBO = (TicketBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.TICKET);
    private final CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.CUSTOMER);
    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.EMPLOYEE);

    public void initialize() throws Exception {
        setCellValueFactory();
        cmbEmployeeId.setItems(employeeBO.getAllEmployeeIds());
        loadTable();
        setNextTicketId();
    }

    private void setCellValueFactory() {
        colTicketId.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        colFullPrice.setCellValueFactory(new PropertyValueFactory<>("fullPrice"));
    }

    private void loadTable() throws Exception {
        ArrayList<TicketDTO> all = ticketBO.getAllTickets();
        ObservableList<TicketTM> list = FXCollections.observableArrayList();
        for (TicketDTO dto : all) {
            list.add(new TicketTM(
                    dto.getTicketId(),
                    dto.getAge(),
                    dto.getPrice(),
                    dto.getDate(),
                    dto.getTime(),
                    dto.getCustomerId(),
                    dto.getEmployeeId(),
                    dto.getQuantity(),
                    dto.getFullPrice()
            ));
        }
        tblTicket.setItems(list);
    }

    private void setNextTicketId() throws Exception {
        lblTicketId.setText(ticketBO.getNextTicketId());
    }

    private void clearFields() throws Exception {
        txtAge.clear();
        txtPrice.clear();
        txtTime.clear();
        txtQuantity.clear();
        lblCustomerId.setText("Enter Customer");
        lblCustomerInfo.setText("Customer Name");
        cmbEmployeeId.getSelectionModel().clearSelection();
        txtCustomerPhone.clear();
        dpDate.setValue(null);
        setNextTicketId();
    }



    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        String customerPhone = txtCustomerPhone.getText();
        try {
            CustomerDTO customer = customerBO.searchCustomerByPhone(customerPhone);
            if (customer != null) {
                lblCustomerId.setText(customer.getId());
                lblCustomerInfo.setText(customer.getName());
                showAlert(Alert.AlertType.INFORMATION, "Customer Found");
            } else {
                showAlert(Alert.AlertType.WARNING, "No Customer Found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error Searching Customer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        TicketDTO dto = getFormData();
        if (ticketBO.saveTicket(dto)) {
            loadTable();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Ticket Saved");
        } else {
            showAlert(Alert.AlertType.ERROR, "Ticket Not Saved");
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        TicketDTO dto = getFormData();
        if (ticketBO.updateTicket(dto)) {
            loadTable();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Ticket Updated");
        } else {
            showAlert(Alert.AlertType.ERROR, "Ticket Not Updated");
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        String id = lblTicketId.getText();

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete?");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (ticketBO.deleteTicket(id)) {
                loadTable();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Ticket Deleted");
            } else {
                showAlert(Alert.AlertType.ERROR, "Ticket Not Deleted");
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws Exception {
        clearFields();
        btnSave1.setDisable(false);
        btnUpdate1.setDisable(true);
        btnDelete1.setDisable(true);
        tblTicket.getSelectionModel().clearSelection();
    }


    public void tblclickOnAction(MouseEvent mouseEvent) {
        TicketTM selected = tblTicket.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblTicketId.setText(selected.getTicketId());
            txtAge.setText(selected.getAge());
            txtPrice.setText(selected.getPrice());
            txtTime.setText(selected.getTime().toString());
            dpDate.setValue(selected.getDate().toLocalDate());
            txtQuantity.setText(selected.getQuantity());
            lblCustomerId.setText(selected.getCustomerId());
            cmbEmployeeId.setValue(selected.getEmployeeId());

            btnSave1.setDisable(true);
            btnUpdate1.setDisable(false);
            btnDelete1.setDisable(false);
        }
    }
    private TicketDTO getFormData() {
        return new TicketDTO(
                lblTicketId.getText(),
                txtAge.getText(),
                txtPrice.getText(),
                Date.valueOf(dpDate.getValue()),
                Time.valueOf(txtTime.getText()),
                lblCustomerId.getText(),
                cmbEmployeeId.getValue(),
                txtQuantity.getText()
        );
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).show();
    }

}
