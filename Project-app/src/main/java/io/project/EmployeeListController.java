package io.project;

import io.project.database.DBManagment;
import io.project.entities.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


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
    private ComboBox<String> facilityCB;

    @FXML
    private TextField searchField;

    ObservableList<Employee> list;

    public void employeeList() {
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salaryInt"));
        ppeCol.setCellValueFactory(new PropertyValueFactory<>("PPE"));

        list = DBManagment.getEmployeeInfo();
        table.setItems(list);

        FilteredList<Employee> filteredData = new FilteredList<>(list, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(employee -> {
            if (newValue == null || newValue.isEmpty())
                return true;

            String lowerCaseFilter = newValue.toLowerCase();

            if (employee.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (employee.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (String.valueOf(employee.getSalaryInt()).contains(lowerCaseFilter)) {
                return true;
            } else if (employee.getCategory().contains(lowerCaseFilter)) {
                return true;
            } else
                return false;
        }));

        SortedList<Employee> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }

    public void onFacilitySelection(){
        if(!facilityCB.getSelectionModel().isEmpty()){
            int facilityId=Integer.parseInt(facilityCB.getSelectionModel().getSelectedItem().split(" | ")[0]);
            ArrayList<Integer> employeeIds =DBManagment.getEmployeeByFacilityID(facilityId);
            table.setItems(FXCollections.observableArrayList(list
                    .stream()
                    .filter(el -> employeeIds.contains(el.getId()))
                    .collect(Collectors.toList()))
            );
        }
        else {
            employeeList();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeList();
        DBManagment.addFacilityToComboBox(facilityCB);
    }
}
