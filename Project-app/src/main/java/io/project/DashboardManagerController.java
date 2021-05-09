package io.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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

    public void mainPage(ActionEvent event) throws IOException {
        try {
            switch (Global.getCurrentUser().getRole()) {
                case "CEO":
                    initPanel(Global.getViewPane(), "CEODetailsView.fxml");
                    break;
                case  "manager":
                    initPanel(Global.getViewPane(), "managerDetailsView.fxml");
                    break;
                case  "employee":
                    initPanel(Global.getViewPane(), "employeeDetailsView.fxml");
                    break;
                case  "accountant":
                    initPanel(Global.getViewPane(), "accountantDetailsView.fxml");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + Global.getCurrentUser().getRole());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Click on Main Page");
    }

    public void openEmployeeList(ActionEvent event) throws IOException {
        initPanel(Global.getViewPane(), "employeeList.fxml");
    }

    public void initPanel(AnchorPane panel, String resource)throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource(resource));
        panel.getChildren().clear();
        panel.getChildren().addAll(fxml);
    }

    public void openFacilityList(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("facilitiesManager.fxml"));
//        Parent root = loader.load();
//        Global.getManagerPane().getChildren().removeAll();
//        Global.getManagerPane().getChildren().addAll(root);
        initPanel( Global.getViewPane(), "facilityList.fxml");
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
