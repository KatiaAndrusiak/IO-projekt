package io.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardManagerController implements Initializable {
    @FXML
    private AnchorPane managerPane;

    @FXML
    private Button mainPageButton;

    @FXML
    private Button employeesButton;

    @FXML
    private Button facilitiesButton;

    @FXML
    private Button receiveDeliveryButton;

    @FXML
    private Button deliveryListButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
