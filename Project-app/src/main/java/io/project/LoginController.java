package io.project;

import io.project.database.DBManagment;
import io.project.entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    @FXML
    void login(ActionEvent event) throws IOException {
        Employee loggedInUser = DBManagment.login(username.getText(), password.getText());
        Global.setCurrentUser(loggedInUser);

        if (loggedInUser != null) {
            Stage primaryStage = (Stage) loginButton.getScene().getWindow();
            primaryStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Stage stage = new Stage();
            stage.setTitle("LEDIKOM Menedżer");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
