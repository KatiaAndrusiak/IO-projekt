package io.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

    public void initPanel(AnchorPane panel, String resource)throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource(resource));
        panel.getChildren().removeAll();
        panel.getChildren().addAll(fxml);
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Global.setViewPane(viewPane);
        Global.setManagerPane(managerPane);
        try {
            switch (Global.getCurrentUser().getRole()) {
                case "CEO":
                    initPanel(managerPane, "dashboardManager.fxml");
                    initPanel(viewPane, "CEODetailsView.fxml");
                    break;
                case  "manager":
                    initPanel(managerPane, "dashboardManager.fxml");
                    initPanel(viewPane, "managerDetailsView.fxml");
                    break;
                case  "employee":
                    initPanel(managerPane, "dashboardManager.fxml");
                    initPanel(viewPane, "employeeDetailsView.fxml");
                    break;
                case  "accountant":
                    initPanel(managerPane, "dashboardManager.fxml");
                    initPanel(viewPane, "accountantDetailsView.fxml");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + Global.getCurrentUser().getRole());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
