package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.screenloader.ChangeScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    @FXML
    private Button employeeDataButton;

    ObservableList<Employee> list;

    Employee selectedItem;


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

        searchInSearchField(list);
    }

    public void searchInSearchField(ObservableList<Employee> list) {
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
        searchField.clear();
        if(!facilityCB.getSelectionModel().isEmpty()){
            int facilityId=Integer.parseInt(facilityCB.getSelectionModel().getSelectedItem().split(" | ")[0]);
            ArrayList<Integer> employeeIds = DBManagment.getEmployeeByFacilityID(facilityId);
            ObservableList<Employee> employeeListByFacility = FXCollections.observableArrayList(list
                                                                .stream()
                                                                .filter(el -> employeeIds.contains(el.getId()))
                                                                .collect(Collectors.toList()));
            table.setItems(employeeListByFacility);
            searchInSearchField(employeeListByFacility);
        }
        else {
            employeeList();
        }
    }

    public void enableEmployeeDataCheck(){
        try {
            if (table.getSelectionModel().getSelectedItem() != null) {
                selectedItem = table.getSelectionModel().getSelectedItem();
                Global.setEmployee(selectedItem);
                employeeDataButton.setDisable(false);
            }
        }
        catch (Exception e){
            AlertBox.errorAlert("Błąd", e.getMessage());
        }
    }

    public void employeeDetails() {
        try {
            ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("employeeDetailsManagerView.fxml")));
        }
        catch (Exception e){
            AlertBox.errorAlert("Błąd", e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        employeeDataButton.setDisable(true);
        employeeList();
        DBManagment.addFacilityToComboBox(facilityCB);
    }
}
