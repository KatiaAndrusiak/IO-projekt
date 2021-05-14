package io.project;

import io.project.screenloader.ChangeScreen;
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

    @FXML
    private Button logoutButton;

    public void mainPage(ActionEvent event) throws IOException {
        Parent ceo = FXMLLoader.load(getClass().getResource("CEODetailsView.fxml"));
        Parent manager = FXMLLoader.load(getClass().getResource("managerDetailsView.fxml"));
        Parent employee = FXMLLoader.load(getClass().getResource("employeeDetailsView.fxml"));
        Parent accountant = FXMLLoader.load(getClass().getResource("accountantDetailsView.fxml"));
        ChangeScreen.openMainPage(ceo, manager, employee, accountant);
    }

    public void openEmployeeList(ActionEvent event) throws IOException {
        ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("employeeList.fxml")));
        ChangeScreen.initPanel(Global.getManagerPane(), FXMLLoader.load(getClass().getResource("employeesManager.fxml")));
    }


    public void openFacilityList(ActionEvent event) throws IOException {
        ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("facilityList.fxml")));
        ChangeScreen.initPanel(Global.getManagerPane(), FXMLLoader.load(getClass().getResource("facilitiesManager.fxml")));
    }

    public void openDeliveryAcceptance(ActionEvent event) throws IOException {
        ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("deliveryAcceptance.fxml")));
    }

    public void openDeliveryList(ActionEvent event) throws IOException {
        ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("deliveryList.fxml")));
    }

    public void logout(ActionEvent event) throws IOException {
        System.out.println("Click logout");
        ChangeScreen.logout(logoutButton, FXMLLoader.load(getClass().getResource("login.fxml")));
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
