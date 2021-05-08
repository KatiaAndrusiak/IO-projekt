package io.project;

import io.project.database.DBManagment;
import io.project.entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    void login(ActionEvent event) {
        Employee test = DBManagment.login("admin", "admin");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
