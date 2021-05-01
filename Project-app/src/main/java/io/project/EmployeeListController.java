package io.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeListController implements Initializable {
    @FXML
    private AnchorPane viewPane;

    @FXML
    private TableView<Employee> table;

    @FXML
    private TableColumn<Employee, String> firstNameCol;

    @FXML
    private TableColumn<Employee, String> lastNameCol;

    @FXML
    private TableColumn<Employee, LocalDate> dobCol;

    @FXML
    private TableColumn<Employee, String> positionCol;

    @FXML
    private TableColumn<Employee, String> categoryCol;

    @FXML
    private TableColumn<Employee, Integer> salaryCol;

    @FXML
    private TableColumn<Employee, LocalDate> ppeCol;

    @FXML
    private ComboBox<Facility> facilityCB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
