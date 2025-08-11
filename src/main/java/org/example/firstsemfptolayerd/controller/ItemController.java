package org.example.firstsemfptolayerd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    public AnchorPane ancItemDash;

    public void btnChemicalOnAction(ActionEvent actionEvent) {
        nevigateTo("/org/example/firstsemfptolayerd/assests/Chemical.fxml");
    }

    public void btnFoodOnAction(ActionEvent actionEvent) {
        nevigateTo("/org/example/firstsemfptolayerd/assests/Food.fxml");
    }

    public void btnFishOnAction(ActionEvent actionEvent) {
        nevigateTo("/org/example/firstsemfptolayerd/assests/Fish.fxml");
    }

    public void btnPlantOnAction(ActionEvent actionEvent) {
        nevigateTo("/org/example/firstsemfptolayerd/assests/Plant.fxml");
    }

    public void btnTankOnAction(ActionEvent actionEvent) {
        nevigateTo("/org/example/firstsemfptolayerd/assests/Tank.fxml");
    }
    private void nevigateTo(String s) {
        try {
            ancItemDash.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(s));

            pane.prefWidthProperty().bind(ancItemDash.widthProperty());
            pane.prefHeightProperty().bind(ancItemDash.heightProperty());

            ancItemDash.getChildren().add(pane);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Page Not Found!").show();
            e.printStackTrace();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nevigateTo("/org/example/firstsemfptolayerd/assests/Fish.fxml");
    }
}
