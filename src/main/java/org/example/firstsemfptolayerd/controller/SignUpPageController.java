package org.example.firstsemfptolayerd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.firstsemfptolayerd.AppInitializer;
import org.example.firstsemfptolayerd.BO.custom.UserBO;
import org.example.firstsemfptolayerd.BO.custom.impl.BOFactory;
import org.example.firstsemfptolayerd.model.UserDTO;


public class SignUpPageController {

    @FXML
    private Button btnSignUp;

    @FXML
    private ChoiceBox<String> choiceRole;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.USER);

    public void initialize() {
        try {
            txtId.setText(getNextId());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Can't get id from database, please try again").show();
            throw new RuntimeException(e);
        }
        choiceRole.getItems().addAll("Admin", "User", "Manager");
        choiceRole.setValue("User");
    }

    @FXML
    void goToLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/org/example/firstsemfptolayerd/assests/Logging.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage newStage = new Stage();
            newStage.setTitle("Login");
            newStage.setScene(scene);
            newStage.show();

            Stage currentStage = (Stage) btnSignUp.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void signUpOnAction(ActionEvent event) {

        if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
            new Alert(Alert.AlertType.ERROR, "Password doesn't match").show();
            return;
        }

        UserDTO userDto = new UserDTO(txtId.getText(), txtName.getText(), txtEmail.getText(), txtPassword.getText(), choiceRole.getValue().toString());
        try {
            userBO.add(userDto);
            new Alert(Alert.AlertType.INFORMATION, "User Saved").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            throw new RuntimeException(e);
        }


    }

    public String getNextId() throws Exception {
        return userBO.generateNewId();
    }

}