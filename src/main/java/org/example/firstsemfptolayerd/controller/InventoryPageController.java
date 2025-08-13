package org.example.firstsemfptolayerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.firstsemfptolayerd.AppInitializer;
import org.example.firstsemfptolayerd.BO.custom.InventoryBO;
import org.example.firstsemfptolayerd.BO.custom.SupplierBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.Dao.EmailUtil;
import org.example.firstsemfptolayerd.entity.*;
import org.example.firstsemfptolayerd.model.*;
import org.example.firstsemfptolayerd.view.tdm.InventoryTM;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InventoryPageController {
    public Label lblInventoryId;
    public DatePicker datePickerDate;
    public TextField txtSupplierPhone;
    public Button btnSearchCustomer;
    public Label SupplierId;
    public Label lblSupplierName;
    public ComboBox<String> cmbItemId;
    public Button btnSearchItem;
    public AnchorPane itemUiLoadPane;
    public Button btnAddToCart;
    public TableView<InventoryTM> tblCart;
    public TableColumn<?,?> colItemId;
    public TableColumn<?,?> colQty;
    public TableColumn<?,?> colUnitPrice;
    public TableColumn<?,?> colInventoryId;
    public TableColumn<?,?> colRemove;
    public Button btnPlaceOrder;

    public static int fishQty = 0, plantQty = 0, chemicalQty = 0, foodQty = 0;

    private final ObservableList<InventoryTM> cartList = FXCollections.observableArrayList();
    private final InventoryBO inventoryBO = (InventoryBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.INVENTORY);
    private final SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.SUPPLIER);

    private PlantDetailController plantDetailPageController;
    private FishDetailController fishDetailPageController;
    private FoodDetailController foodDetailPageController;
    private ChemicalDetailController chemDetailPageController;

    public void initialize() throws SQLException, ClassNotFoundException {
        lblInventoryId.setText(inventoryBO.generateNextInventoryId());
        cmbItemId.setItems(FXCollections.observableArrayList("Plant", "Fish", "Chemical", "Food"));
        setupTableColumns();
    }

    private void setupTableColumns() {
        colInventoryId.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("btn"));
        tblCart.setItems(cartList);
    }
    public void btnSearchSupplierOnAction(ActionEvent actionEvent) {
        try {
            Supplier supplier = supplierBO.searchSupplierByPhone(txtSupplierPhone.getText());
            if (supplier != null) {
                SupplierId.setText(supplier.getSupId());
                lblSupplierName.setText(supplier.getName());
                showAlert(Alert.AlertType.INFORMATION, "Supplier Found");
            } else {
                showAlert(Alert.AlertType.WARNING, "Supplier Not Found");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
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

            switch (selectedItem) {
                case "Plant" -> {
                    fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/org/example/firstsemfptolayerd/assests/PlantDetail.fxml"));
                    pane = fxmlLoader.load();
                    plantDetailPageController = fxmlLoader.getController();
                    plantDetailPageController.loadPlantIds();
                }
                case "Fish" -> {
                    fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/org/example/firstsemfptolayerd/assests/FishDetail.fxml"));
                    pane = fxmlLoader.load();
                    fishDetailPageController = fxmlLoader.getController();
                    fishDetailPageController.loadFishIds();
                }
                case "Chemical" -> {
                    fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/org/example/firstsemfptolayerd/assests/ChemicalDetail.fxml"));
                    pane = fxmlLoader.load();
                    chemDetailPageController = fxmlLoader.getController();
                    chemDetailPageController.loadChemicalIds();
                }
                case "Food" -> {
                    fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/org/example/firstsemfptolayerd/assests/FoodDetail.fxml"));
                    pane = fxmlLoader.load();
                    foodDetailPageController = fxmlLoader.getController();
                    foodDetailPageController.loadFoodIds();
                }
                default -> {
                    showAlert(Alert.AlertType.WARNING, "Invalid item type selected.");
                    return;
                }
            }

            pane.prefWidthProperty().bind(itemUiLoadPane.widthProperty());
            pane.prefHeightProperty().bind(itemUiLoadPane.heightProperty());
            itemUiLoadPane.getChildren().add(pane);

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error loading the selected item page.");
        }
    }

    public void btnAddtoCartOnAction(ActionEvent actionEvent) {
        String selectedItem = cmbItemId.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "Please select an item type first.");
            return;
        }

        String itemId = null, qty = null, unitPrice = null;
        try {
            switch (selectedItem) {
                case "Plant" -> {
                    if (plantDetailPageController == null) {
                        showAlert(Alert.AlertType.ERROR, "Plant UI not loaded.");
                        return;
                    }
                    itemId = plantDetailPageController.getSelectedPlantId();
                    qty = plantDetailPageController.getQuantity();
                    unitPrice = plantDetailPageController.getUnitPrice();
                }
                case "Fish" -> {
                    if (fishDetailPageController == null) {
                        showAlert(Alert.AlertType.ERROR, "Fish UI not loaded.");
                        return;
                    }
                    itemId = fishDetailPageController.getSelectedFishId();
                    qty = fishDetailPageController.getQuantity();
                    unitPrice = fishDetailPageController.getUnitPrice();
                }
                case "Chemical" -> {
                    if (chemDetailPageController == null) {
                        showAlert(Alert.AlertType.ERROR, "Chemical UI not loaded.");
                        return;
                    }
                    itemId = chemDetailPageController.getSelectedChemicalId();
                    qty = chemDetailPageController.getQuantity();
                    unitPrice = chemDetailPageController.getUnitPrice();
                }
                case "Food" -> {
                    if (foodDetailPageController == null) {
                        showAlert(Alert.AlertType.ERROR, "Food UI not loaded.");
                        return;
                    }
                    itemId = foodDetailPageController.getSelectedFoodId();
                    qty = foodDetailPageController.getQuantity();
                    unitPrice = foodDetailPageController.getUnitPrice();
                }
            }

            Button btn = new Button("Remove");
            InventoryTM tm = new InventoryTM(lblInventoryId.getText(), itemId, qty, unitPrice, btn);
            btn.setOnAction(e -> cartList.remove(tm));
            cartList.add(tm);

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error adding item to cart.");
        }
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        if (datePickerDate.getValue() == null || cartList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please select a date and add items to the cart.");
            return;
        }

        try {
            Inventory inventory = new Inventory(
                    lblInventoryId.getText(),
                    Date.valueOf(datePickerDate.getValue()).toString(),
                    SupplierId.getText(),
                    cmbItemId.getValue()
            );

            Plant plant = new Plant(); plant.setQuantity(String.valueOf(plantQty));
            Fish fish = new Fish(); fish.setQuantity(String.valueOf(fishQty));
            Chemical chemical = new Chemical(); chemical.setQuantity(String.valueOf(chemicalQty));
            Food food = new Food(); food.setQuantity(String.valueOf(foodQty));
            Map<String, Integer> updatedQuantities = new HashMap<>();
            boolean isSaved = inventoryBO.placeInventoryOrder(
                    inventory, new ArrayList<>(cartList), updatedQuantities, fish, plant, chemical, food
            );


            if (isSaved) {
                String email = String.valueOf(supplierBO.getSupplierEmailById(SupplierId.getText()));
                if (email != null && !email.isEmpty()) {
                    EmailUtil.sendSupplierstockEmail(email, lblSupplierName.getText(), 0);
                }
                showAlert(Alert.AlertType.INFORMATION, "Inventory placed successfully!");
                clearFields();
                lblInventoryId.setText(inventoryBO.generateNextInventoryId());
            }

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        datePickerDate.setValue(null);
        cmbItemId.getSelectionModel().clearSelection();
        txtSupplierPhone.clear();
        SupplierId.setText("Supplier ID");
        lblSupplierName.setText("Supplier Name");
        cartList.clear();
        fishQty = 0; plantQty = 0; chemicalQty = 0; foodQty = 0;
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).show();
    }
}