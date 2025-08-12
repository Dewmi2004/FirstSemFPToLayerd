package org.example.firstsemfptolayerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.firstsemfptolayerd.AppInitializer;
import org.example.firstsemfptolayerd.BO.custom.CustomerBO;
import org.example.firstsemfptolayerd.BO.custom.OrderBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.Dao.EmailUtil;
import org.example.firstsemfptolayerd.entity.Fish;
import org.example.firstsemfptolayerd.entity.Order;
import org.example.firstsemfptolayerd.entity.Plant;
import org.example.firstsemfptolayerd.model.*;
import org.example.firstsemfptolayerd.view.tdm.CartTM;

import java.sql.Date;
import java.util.ArrayList;

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
    public TableView<CartTM> tblCart;
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
    private final OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.ORDER);
    private final CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.CUSTOMER);
    private final ObservableList<CartTM> cartList = FXCollections.observableArrayList();

    public String customerEmail = "";
    public static String fishId = "";
    public static int fishQty = 0;
    public static String plantId = "";
    public static int plantQty = 0;
    double total = 0;

    private PlantCartController plantCartController;
    private FishCartController fishCartController;
    public void initialize() throws Exception {
        setNextOrderId();
        setNextPaymentId();
        loadItemTypes();
        setupTableColumns();
    }

    private void loadItemTypes() {
        cmbItemId.setItems(FXCollections.observableArrayList("Plant Order", "Fish Order"));
        cmbMethod.setItems(FXCollections.observableArrayList("Card", "Cash"));
    }

    private void setupTableColumns() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("btn"));
        tblCart.setItems(cartList);
    }

    private void setNextOrderId() throws Exception {
        lblOrderrid.setText(orderBO.generateNextOrderId());
    }

    private void setNextPaymentId() throws Exception {
        lblPaymentId.setText(orderBO.generateNextPaymentId());
    }

    public void btnAddtoCartOnAction(ActionEvent actionEvent) {

        String selectedItemType = cmbItemId.getSelectionModel().getSelectedItem();

        if (selectedItemType == null) {
            showAlert(Alert.AlertType.WARNING, "Please select an item type first.");
            return;
        }

        try {
            String itemId, name, qtyStr, unitPriceStr;

            if (selectedItemType.equals("Fish Order")) {
                if (fishCartController == null) {
                    showAlert(Alert.AlertType.WARNING, "Please load the Fish Order form first.");
                    return;
                }
                itemId = fishCartController.getSelectedFishId();
                name = fishCartController.getFishName();
                qtyStr = fishCartController.getQuantity();
                unitPriceStr = fishCartController.getUnitPrice();
            } else {
                if (plantCartController == null) {
                    showAlert(Alert.AlertType.WARNING, "Please load the Plant Order form first.");
                    return;
                }
                itemId = plantCartController.getSelectedPlantId();
                name = plantCartController.getPlantName();
                qtyStr = plantCartController.getQuantity();
                unitPriceStr = plantCartController.getUnitPrice();
            }

            int quantity = Integer.parseInt(qtyStr);
            double unitPrice = Double.parseDouble(unitPriceStr);
            double total = quantity * unitPrice;

            for (CartTM tm : cartList) {
                if (tm.getItemId().equals(itemId)) {
                    showAlert(Alert.AlertType.WARNING, "Item already in cart.");
                    return;
                }
            }

            Button btnRemove = new Button("Remove");
            CartTM cartTM = new CartTM(itemId, name, qtyStr, unitPriceStr, String.valueOf(total), btnRemove);

            btnRemove.setOnAction(e -> {
                cartList.remove(cartTM);
                calculateTotal();
            });

            cartList.add(cartTM);
            calculateTotal();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
        String selectedItem = cmbItemId.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "Please select an item type first.");
            return;
        }

        try {
            itemUiLoadPane.getChildren().clear();
            FXMLLoader fxmlLoader;
            AnchorPane pane;

            if (selectedItem.equals("Plant Order")) {
                fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/org/example/firstsemfptolayerd/assests/PlantCartPage.fxml"));
                pane = fxmlLoader.load();
                plantCartController = fxmlLoader.getController();
                plantCartController.loadPlantIds();
            } else {
                fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/org/example/firstsemfptolayerd/assests/FishCartPage.fxml"));
                pane = fxmlLoader.load();
                fishCartController = fxmlLoader.getController();
                fishCartController.loadFishIds();
            }

            pane.prefWidthProperty().bind(itemUiLoadPane.widthProperty());
            pane.prefHeightProperty().bind(itemUiLoadPane.heightProperty());
            itemUiLoadPane.getChildren().add(pane);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        try {
            CustomerDTO customer = customerBO.searchCustomerByPhone(txtCustomerPhone.getText());
            if (customer != null) {
                CustomerId.setText(customer.getId());
                lblCustomerName.setText(customer.getName());
                lblcustomerEmail.setText(customer.getEmail());
                showAlert(Alert.AlertType.INFORMATION, "Customer Found");
            } else {
                showAlert(Alert.AlertType.WARNING, "Customer Not Found");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }
    private void calculateTotal() {
        total = 0;
        for (CartTM tm : cartList) {
            total += Double.parseDouble(tm.getTotal().replace("Rs. ", ""));
        }
        lblTotalAmount.setText(String.format("Rs. %.2f", total));
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        if (datePickerDate.getValue() == null || cmbItemId.getValue() == null || cartList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please complete the form and add items to cart.");
            return;
        }

        try {
            Order order = new Order(
                    lblOrderrid.getText(),
                    lblPaymentId.getText(),
                    Date.valueOf(datePickerDate.getValue()),
                    CustomerId.getText(),
                    cmbItemId.getValue(),
                    cmbMethod.getValue(),
                    lblTotalAmount.getText()
            );

            Plant plant = new Plant(plantId, null, null, null, null, String.valueOf(plantQty));
            Fish fish = new Fish(fishId, null, null, null, null, null, null, null, String.valueOf(fishQty));

            ArrayList<CartDTO> cartDTOList = new ArrayList<>();
            for (CartTM tm : cartList) {
                cartDTOList.add(new CartDTO(tm.getItemId(), tm.getName(), tm.getQuantity(), tm.getUnitPrice(), tm.getTotal()));
            }

            boolean isPlaced = orderBO.placeOrder(order, fish, plant, cartDTOList, total, lblcustomerEmail.getText());
            if (isPlaced) {
                EmailUtil.sendOrderAllert(total,lblcustomerEmail.getText(), CustomerId.getText());
                showAlert(Alert.AlertType.INFORMATION, "Order placed successfully!");
                clearFields();
                setNextOrderId();
                setNextPaymentId();
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed to place order.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }
    private void clearFields() {
        datePickerDate.setValue(null);
        cmbItemId.getSelectionModel().clearSelection();
        txtCustomerPhone.clear();
        CustomerId.setText("Customer ID");
        lblCustomerName.setText("Customer Name");
        cartList.clear();
        lblTotalAmount.setText("Rs. 0.00");
        fishId = "";
        fishQty = 0;
        plantId = "";
        plantQty = 0;
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).show();
    }

    public void btnCheckBalanceOnAction(ActionEvent actionEvent) {
        try {
            double paid = Double.parseDouble(txtPaidAmount.getText());
            double total = Double.parseDouble(lblTotalAmount.getText().replace("Rs. ", ""));
            lblChange.setText(String.format("Rs. %.2f", paid - total));
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Invalid amount");
        }
    }
}
