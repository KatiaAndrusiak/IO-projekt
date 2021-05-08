package io.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
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

    public void mainPage(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        System.out.println("Click on Main Page");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        switch (Global.getCurrentUser().getRole()) {
            case "CEO":
                employeesButton.setVisible(true);
                facilitiesButton.setVisible(true);
                break;
            case  "manager":
                receiveDeliveryButton.setVisible(true);
                break;
            case  "employee":
                break;
            case  "accountant":
                deliveryListButton.setVisible(true);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + Global.getCurrentUser().getRole());
        }
    }
}
