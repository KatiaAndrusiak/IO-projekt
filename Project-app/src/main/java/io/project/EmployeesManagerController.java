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

public class EmployeesManagerController implements Initializable {

    @FXML
    private AnchorPane managerPane;

    @FXML
    private Button mainPageButton;

    @FXML
    private Button employeeListButton;

    @FXML
    private Button coursesButton;

    @FXML
    private Button employeeAddButton;

    @FXML
    private  Button logoutButton;

    public void mainPage(ActionEvent event) throws IOException {
        Parent ceo = FXMLLoader.load(getClass().getResource("CEODetailsView.fxml"));
        ChangeScreen.openMainPage(ceo,  null, null, null);
        ChangeScreen.initPanel(Global.getManagerPane(), FXMLLoader.load(getClass().getResource("dashboardManager.fxml")));
    }

    public void openEmployeeList(ActionEvent event) throws IOException {
        ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("employeeList.fxml")));
    }

    public void openCourseList(ActionEvent event) throws IOException {
        ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("courseList.fxml")));
    }

    public void openEmployeeAddition(ActionEvent event) throws IOException {
        ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("employeeAddition.fxml")));
    }

    public void logout(ActionEvent event) throws IOException {
        System.out.println("Click logout");
        ChangeScreen.logout(logoutButton, FXMLLoader.load(getClass().getResource("login.fxml")));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
