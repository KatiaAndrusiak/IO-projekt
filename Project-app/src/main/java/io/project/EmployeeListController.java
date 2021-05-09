package io.project;

import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.entities.Facility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    public void employeeList() {
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        dobCol.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("DOB"));
        positionCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("category"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salaryInt"));
        ppeCol.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("PPE"));

        ObservableList<Employee> list = FXCollections.observableArrayList();
        list = DBManagment.getEmployeeInfo();
        table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeList();
    }
}
