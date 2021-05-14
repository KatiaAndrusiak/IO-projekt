package io.project;

import io.project.screenloader.ChangeScreen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane managerPane;

    @FXML
    private AnchorPane viewPane;

    public AnchorPane getManagerPane() {
        return managerPane;
    }

    public void setManagerPane(AnchorPane managerPane) {
        this.managerPane = managerPane;
    }

    public AnchorPane getViewPane() {
        return viewPane;
    }

    public void setViewPane(AnchorPane viewPane) {
        this.viewPane = viewPane;
    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Global.setViewPane(viewPane);
        Global.setManagerPane(managerPane);
        try {
            switch (Global.getCurrentUser().getRole()) {
                case "CEO":
                    ChangeScreen.initPanel(managerPane, FXMLLoader.load(getClass().getResource("dashboardManager.fxml")));
                    ChangeScreen.initPanel(viewPane, FXMLLoader.load(getClass().getResource("CEODetailsView.fxml")));
                    break;
                case  "manager":
                    ChangeScreen.initPanel(managerPane, FXMLLoader.load(getClass().getResource("dashboardManager.fxml")));
                    ChangeScreen.initPanel(viewPane, FXMLLoader.load(getClass().getResource("managerDetailsView.fxml")));
                    break;
                case  "employee":
                    ChangeScreen.initPanel(managerPane, FXMLLoader.load(getClass().getResource("dashboardManager.fxml")));
                    ChangeScreen.initPanel(viewPane, FXMLLoader.load(getClass().getResource("employeeDetailsView.fxml")));
                    break;
                case  "accountant":
                    ChangeScreen.initPanel(managerPane, FXMLLoader.load(getClass().getResource("dashboardManager.fxml")));
                    ChangeScreen.initPanel(viewPane, FXMLLoader.load(getClass().getResource("accountantDetailsView.fxml")));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + Global.getCurrentUser().getRole());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
