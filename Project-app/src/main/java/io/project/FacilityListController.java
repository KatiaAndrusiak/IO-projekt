package io.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FacilityListController implements Initializable {
    @FXML
    private AnchorPane viewPane;

    @FXML
    private TableView<Facility> table;

    @FXML
    private TableColumn<Facility, String> cityCol;

    @FXML
    private TableColumn<Facility, String> nameCol;

    @FXML
    private TableColumn<Facility, String> addressCol;

    @FXML
    private TableColumn<Facility, String> scheduleCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
