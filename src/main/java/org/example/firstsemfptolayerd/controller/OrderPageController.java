package org.example.firstsemfptolayerd.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.firstsemfptolayerd.view.tdm.OrderTM;

public class OrderPageController {
    public Label lblOrderrid;
    public DatePicker datePickerDate;
    public TextField txtCustomerPhone;
    public Button btnSearchCustomer;
    public Label CustomerId;
    public Label lblCustomerName;
    public Label lblcustomerEmail;
    public ComboBox<String> cmbItemId;
    public Button btnSearchItem;
    public AnchorPane itemUiLoadPane;
    public Button btnAddToCart;
    public TableView<OrderTM> tblCart;
    public TableColumn<?,?> colItemId;
    public TableColumn<?,?> colName;
    public TableColumn<?,?> colQty;
    public TableColumn<?,?> colUnitPrice;
    public TableColumn<?,?> colTotal;
    public TableColumn<?,?> colRemove;
    public Label lblPaymentId;
    public ComboBox<String> cmbMethod;
    public Label lblTotalAmount;
    public TextField txtPaidAmount;
    public Button btnCheckBalance;
    public Label lblChange;
    public Button btnPlaceOrder;

    public void btnAddtoCartOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnCheckBalanceOnAction(ActionEvent actionEvent) {

    }
}
