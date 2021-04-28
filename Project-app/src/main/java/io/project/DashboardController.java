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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/dashboardManager.fxml"));
        try {
            Parent root = loader.load();
            managerPane.getChildren().clear();
            managerPane.getChildren().addAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
