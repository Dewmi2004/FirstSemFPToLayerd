package org.example.firstsemfptolayerd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    @FXML
    private AnchorPane ancDash;

    @FXML
    private AnchorPane ancPanal;

    @FXML
    private void btnCustomerOnAction(ActionEvent event) {
        nevigateTo("/org/example/firstsemfptolayerd/assests/Customer.fxml");
    };

    @FXML
    private void btnEmployeeOnAction(ActionEvent event) {
       nevigateTo("/org/example/firstsemfptolayerd/assests/Employee.fxml");
    };

    @FXML
    private Label lblDash;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {nevigateTo("/org/example/firstsemfptolayerd/assests/Customer.fxml");}

    private void nevigateTo(String s) {
        try {
            ancPanal.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(s));

            pane.prefWidthProperty().bind(ancPanal.widthProperty());
            pane.prefHeightProperty().bind(ancPanal.heightProperty());

            ancPanal.getChildren().add(pane);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Page Not Found!").show();
            e.printStackTrace();

        }
    }

    public void btnsupplierOnAction(ActionEvent actionEvent) {
nevigateTo("/org/example/firstsemfptolayerd/assests/Supplier.fxml");    }

    public void btnOrderOnAction(ActionEvent actionEvent) {
      //  nevigateTo("/view/Order.fxml");
    }

    public void btnTicketOnAction(ActionEvent actionEvent) {nevigateTo("/view/Ticket.fxml");}

    public void btnInventoryOnAction(ActionEvent actionEvent) {
        //nevigateTo("/view/Inventory.fxml");
    }

    public void btnItemOnAction(ActionEvent actionEvent) {
       // nevigateTo("/view/Item.fxml");
    }

    public void btnPHLevelOnAction(ActionEvent actionEvent) {
       // nevigateTo("/view/PHChemical.fxml");
    }
}