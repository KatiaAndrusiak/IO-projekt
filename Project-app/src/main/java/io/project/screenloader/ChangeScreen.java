package io.project.screenloader;

import io.project.Global;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeScreen {

    public static void initPanel(AnchorPane panel,  Parent fxml)throws IOException {
        panel.getChildren().clear();
        panel.getChildren().addAll(fxml);
    }

    public static void openMainPage(Parent ceo, Parent manager, Parent employee, Parent accountant) {
        try {
            switch (Global.getCurrentUser().getRole()) {
                case "CEO":
                    ChangeScreen.initPanel(Global.getViewPane(), ceo);
                    break;
                case  "manager":
                    ChangeScreen.initPanel(Global.getViewPane(), manager);
                    break;
                case  "employee":
                    ChangeScreen.initPanel(Global.getViewPane(), employee);
                    break;
                case  "accountant":
                    ChangeScreen.initPanel(Global.getViewPane(), accountant);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + Global.getCurrentUser().getRole());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Click on Main Page");
    }

    public static void logout(Button button, Parent fxml) {
        Stage primaryStage = (Stage) button.getScene().getWindow();
        primaryStage.close();
        Stage stage = new Stage();
        stage.setTitle("Panel logowania");
        stage.setScene(new Scene(fxml));
        stage.show();
    }
}
