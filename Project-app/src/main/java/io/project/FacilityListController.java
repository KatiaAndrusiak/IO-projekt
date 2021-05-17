package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.entities.Facility;
import io.project.entities.Holiday;
import io.project.validation.CheckAndClearTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @FXML
    private Button addInspectionButton;

    @FXML
    private TextField employeeTF;

    @FXML
    private DatePicker inspectionDate;

    @FXML
    private TextField inspectionDescription;

    @FXML
    private Label questionLabel;

    @FXML
    private Label question1Label;

    @FXML
    private Label question2Label;

    @FXML
    private Label question3Label;

    @FXML
    private ComboBox<String> answer1;

    @FXML
    private ComboBox<String> answer2;

    @FXML
    private ComboBox<String> answer3;

    @FXML
    private TextField employeeText1;

    @FXML
    private TextField employeeText2;

    @FXML
    private TextField employeeText3;

    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;

    @FXML
    private DatePicker date3;

    @FXML
    private TextField descriptionText1;

    @FXML
    private TextField descriptionText3;

    @FXML
    private TextField descriptionText2;

    @FXML
    private Button addButtonHoliday;

    @FXML
    private VBox holidayForm;

    @FXML
    private ComboBox<String> holidayName;

    @FXML
    private DatePicker holidayDate;

    @FXML
    private TextField holidayProceeds;

    @FXML
    private ComboBox<Employee> holidayEmployee;

    @FXML
    private Button listInspectionButton;

    @FXML
    private VBox inspectionForm;

    @FXML
    private HBox inspectionQuestionsForm;

    @FXML
    private ListView<String> tableOptions;

    @FXML
    private Button listHolidayButton;

    @FXML
    private Button listEmployeeButton;

    @FXML
    private VBox employeeForm;

    @FXML
    private TableView<Employee> tableEmployee;

    @FXML
    private TableColumn<Employee, String> employeeFirstNameCol;

    @FXML
    private TableColumn<Employee, String> employeeLastNameCol;

    @FXML
    private TableColumn<Employee, String> employeePositionCol;

    @FXML
    private Button employeeInfoButton;

    @FXML
    private Button listDeliveriesButton;

    @FXML
    private Button addEmployeeButton;

    @FXML
    private ComboBox<Employee> addEmployeeCB;

    private Facility selectedFacility;


    public void facilityList(){
        cityCol.setCellValueFactory(new PropertyValueFactory<Facility,String>("city"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Facility,String>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Facility,String>("address"));
        scheduleCol.setCellValueFactory(new PropertyValueFactory<Facility,String>("schedule"));

        ObservableList<Facility> list = DBManagment.getFacilityInfo();
        table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBManagment.fillHolidayAdditionDataEmployee(holidayEmployee);
        DBManagment.fillHolidayAdditionDataHoliday(holidayName);
        facilityList();
    }

    @FXML
    private void initAddEmployeeComboBox(MouseEvent mouseevent ) {
        if (table.getSelectionModel().getSelectedIndex() < 0)
        {
            return;
        }
        selectedFacility = table.getSelectionModel().getSelectedItem();
        displayEmployeeComboBox();
    }

    public void addEmployeeToFacility(ActionEvent event) {
        int facilityId = selectedFacility.getId();
        int employeeId = addEmployeeCB.getSelectionModel().getSelectedItem().getId();
        if (DBManagment.addEmployeeToFacility(employeeId, facilityId)) {
            AlertBox.infoAlert("Udało się!", "Pracownik o id " + employeeId + "  został dodany do obiektu o id " + facilityId + ".", "success");
        }

        displayEmployeeComboBox();

    }

    private void displayEmployeeComboBox() {
        try {
            List<Employee> dbEmployees = DBManagment.getEmployees();
            List<Employee> facilityEmployees = DBManagment.getEmployeesByFacilityID(selectedFacility.getId());
            List<Employee> diff = dbEmployees.stream()
                    .filter(e -> ! (facilityEmployees.contains(e)))
                    .collect (Collectors.toList());

            Map<String, Employee> employeeMap = diff.stream().collect(Collectors.toMap(this::employeeComboboxToString, Function.identity(), (e1, e2) -> e1));
            addEmployeeCB.setItems(FXCollections.observableList(diff));
            addEmployeeCB.setConverter(new StringConverter<>() {
                @Override
                public String toString(Employee employee) {
                    return employeeComboboxToString(employee);
                }

                @Override
                public Employee fromString(String s) {
                    return employeeMap.get(s);
                }
            });
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }


    private String employeeComboboxToString(Employee employee) {
        return employee.getFirstName() + " " + employee.getLastName() + " " + employee.getId();
    }

    // po kliknieciu addButtonHoliday ma sie pokazac holidayForm,
    // po kliknieciu listInspectionButton ma sie pokazac inspectionForm i inspectionQuestionsForm
    // po kliknieciu addEmployeeButton ma sie pokazac employeeForm
    public void addHoliday(ActionEvent actionEvent) {
        Holiday holidayToAdd = new Holiday();
        holidayToAdd.setName(holidayName.getSelectionModel().getSelectedItem());
        holidayToAdd.setProceeds(Double.parseDouble(holidayProceeds.getText()));
        holidayToAdd.setEmployee(holidayEmployee.getSelectionModel().getSelectedItem());
        holidayToAdd.setDate(holidayDate.getValue());
        holidayToAdd.setFacility(table.getSelectionModel().getSelectedItem());
        if (DBManagment.addHoliday(holidayToAdd)) {
            AlertBox.infoAlert("Udało się!", "Dodano święto " + holidayToAdd.getName() + ", do pracownika " + holidayToAdd.getEmployee().getFirstName() + ", " + holidayToAdd.getEmployee().getFirstName() , "Obiekt został dodany do bazy");
            CheckAndClearTextField.clearTextField(holidayProceeds);
        }else{
            AlertBox.infoAlert("Ups", "Nie udało się dodać święta", "Obiekt nie został dodany");
        }
    }
}
