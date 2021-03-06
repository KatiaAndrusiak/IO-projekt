package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.entities.Facility;
import io.project.entities.Holiday;
import io.project.screenloader.ChangeScreen;
import io.project.validation.FieldValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FacilityListController implements Initializable {

    @FXML
    public HBox additionComboBoxes;

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
    private Label addInspectionLabel;

    @FXML
    private ComboBox<Employee> employeeTF;

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
    private ComboBox<Employee> empInspectionQuestion1;

    @FXML
    private ComboBox<Employee> empInspectionQuestion2;

    @FXML
    private ComboBox<Employee> empInspectionQuestion3;

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
    private Label addLabelHoliday;

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
    private Label addEmployeeLabel;

    @FXML
    private ComboBox<Employee> addEmployeeCB;

    private Facility selectedFacility;

    private Employee selectedEmployee;

    public void facilityList() {
        cityCol.setCellValueFactory(new PropertyValueFactory<Facility, String>("city"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Facility, String>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Facility, String>("address"));
        scheduleCol.setCellValueFactory(new PropertyValueFactory<Facility, String>("schedule"));

        ObservableList<Facility> list = DBManagment.getFacilityInfo();
        table.setItems(list);
    }

    public void employeeListByFacility() {
        employeeFirstNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        employeeLastNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        employeePositionCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
        ObservableList<Employee> list = DBManagment.getEmployeeInfoByFacilityId(selectedFacility.getId());
        tableEmployee.setItems(list);
    }

    public void holidayListByFacility() {
        ObservableList<String> data = DBManagment.getHolidayInfoByFacilityID(selectedFacility.getId());
        tableOptions.setItems(data);
    }

    public void deliveryListByFacility() {
        ObservableList<String> data = DBManagment.getDeliveryInfoByFacilityID(selectedFacility.getId());
        tableOptions.setItems(data);
    }

    public void inspectionListByFacility() {
        ObservableList<String> data = DBManagment.getInspectionInfoByFacilityID(selectedFacility.getId());
        tableOptions.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inspectionQuestionsForm.setVisible(false);
        additionComboBoxes.setVisible(false);
        employeeInfoButton.setDisable(true);
        listEmployeeButton.setDisable(true);
        DBManagment.fillHolidayAdditionDataEmployee(holidayEmployee);
        DBManagment.fillHolidayAdditionDataHoliday(holidayName);
        DBManagment.fillHolidayAdditionDataEmployee(employeeTF);
        setQuestion1Visibility(false);
        setQuestion2Visibility(false);
        setQuestion3Visibility(false);
        initInspectionQuestions();
        facilityList();
    }

    void setQuestion1Visibility(boolean isVisible) {
        empInspectionQuestion1.setVisible(isVisible);
        date1.setVisible(isVisible);
        descriptionText1.setVisible(isVisible);
    }

    ;

    void setQuestion2Visibility(boolean isVisible) {
        empInspectionQuestion2.setVisible(isVisible);
        date2.setVisible(isVisible);
        descriptionText2.setVisible(isVisible);
    }

    ;

    void setQuestion3Visibility(boolean isVisible) {
        empInspectionQuestion3.setVisible(isVisible);
        date3.setVisible(isVisible);
        descriptionText3.setVisible(isVisible);
    }

    ;

    /* inspection questions should be grouped in HBox, not VBox */
    private void initInspectionQuestions() {
        answer1.getItems().add("Tak");
        answer1.getItems().add("Nie");
        answer2.getItems().add("Tak");
        answer2.getItems().add("Nie");
        answer3.getItems().add("Tak");
        answer3.getItems().add("Nie");
        DBManagment.fillHolidayAdditionDataEmployee(empInspectionQuestion1);
        /* name of method above is misleading. It works generically, not only for holiday addition */
        DBManagment.fillHolidayAdditionDataEmployee(empInspectionQuestion2);
        DBManagment.fillHolidayAdditionDataEmployee(empInspectionQuestion3);
    }

    @FXML
    private void initAddEmployeeComboBox(MouseEvent mouseevent) {
        if (table.getSelectionModel().getSelectedIndex() < 0) {
            return;
        }
        listEmployeeButton.setDisable(false);
        selectedFacility = table.getSelectionModel().getSelectedItem();
        displayEmployeeComboBox();
        clearAdditionData();
        clearinspection();
        setVisibilityInternal();
    }

    public void clearAdditionData(){
        tableOptions.setItems(null);
        tableEmployee.setItems(null);
        employeeTF.setValue(null);
        holidayName.setValue(null);
        holidayEmployee.setValue(null);
        holidayDate.setValue(null);
        addEmployeeCB.setValue(null);
        holidayProceeds.clear();
    }
    public void addEmployeeToFacility(ActionEvent event) {
        int facilityId = selectedFacility.getId();
        int employeeId = addEmployeeCB.getSelectionModel().getSelectedItem().getId();
        if (DBManagment.addEmployeeToFacility(employeeId, facilityId)) {
            AlertBox.infoAlert("Uda??o si??!", "Pracownik o id " + employeeId + "  zosta?? dodany do obiektu o id " + facilityId + ".", "success");
        }

        displayEmployeeComboBox();

    }

    public void addInspection(ActionEvent event) {
        String s1 = "Czy wilgotno???? w pomieszczeniu jest zgodna z zasadami?";
        String s2 = "Czy wszystkie lod??wki s?? sprawne?";
        String s3 = "Czy wszyscy pracownicy s?? wyposa??eni w ??OO?";
        boolean isEverythingValid = true;
        HashSet<Boolean> validation = new HashSet<Boolean>();

        if (FieldValidation.validateCharField(inspectionDescription) && FieldValidation.validateComboBox(employeeTF)
                && FieldValidation.validateDatePicker(inspectionDate) && FieldValidation.validateComboBox(answer1)
                && FieldValidation.validateComboBox(answer2) && FieldValidation.validateComboBox(answer3)) {

            String ans1 = answer1.getSelectionModel().getSelectedItem();
            String ans2 = answer2.getSelectionModel().getSelectedItem();
            String ans3 = answer3.getSelectionModel().getSelectedItem();

            int facilityId = selectedFacility.getId();
            int employeeId = employeeTF.getSelectionModel().getSelectedItem().getId();
            int empInspection1 = -1;
            int empInspection2 = -1;
            int empInspection3 = -1;
            Date date = Date.valueOf(inspectionDate.getValue());
            String descr = inspectionDescription.getText();

            if ((ans1.equals("Nie") && !FieldValidation.validateDatePicker(date1)) ||
                    (ans2.equals("Nie") && !FieldValidation.validateDatePicker(date2)) ||
                    (ans3.equals("Nie") && !FieldValidation.validateDatePicker(date3))) {
                isEverythingValid = false;
            }

            if (isEverythingValid) {
                if (ans1.equals("Nie")) {
                    if (FieldValidation.validateDatePicker(date1) && FieldValidation.validateComboBox(empInspectionQuestion1))
                        empInspection1 = empInspectionQuestion1.getSelectionModel().getSelectedItem().getId();
                        validation.add(DBManagment.addCheckup(s1, ans1, Date.valueOf(date1.getValue()), empInspection1, descriptionText1.getText()));
                } else validation.add(DBManagment.addCheckup(s1, ans1, null, 0, null));

                if (ans2.equals("Nie")) {
                    if (FieldValidation.validateDatePicker(date2) && FieldValidation.validateComboBox(empInspectionQuestion2))
                        empInspection2 = empInspectionQuestion2.getSelectionModel().getSelectedItem().getId();
                        validation.add(DBManagment.addCheckup(s2, ans2, Date.valueOf(date2.getValue()), empInspection2, descriptionText2.getText()));
                } else validation.add(DBManagment.addCheckup(s2, ans2, null, 0, null));

                if (ans3.equals("Nie")) {
                    if (FieldValidation.validateDatePicker(date3) && FieldValidation.validateComboBox(empInspectionQuestion3))
                        empInspection3 = empInspectionQuestion3.getSelectionModel().getSelectedItem().getId();
                        validation.add(DBManagment.addCheckup(s3, ans3, Date.valueOf(date3.getValue()), empInspection3, descriptionText3.getText()));
                } else validation.add(DBManagment.addCheckup(s3, ans3, null, 0, null));

                if (validation.contains(false)) {
                    AlertBox.infoAlert("Blad", "Operacje na bazie danych nie powiodly sie", "failure");
                    return;
                }

                if (DBManagment.addInspection(employeeId, facilityId, date, descr)) {
                    AlertBox.infoAlert("Uda??o si??!", "Inspekcja dodana do pracownika o id " + employeeId + " na obiekcie o id " + facilityId + ".", "success");
                }
            }
        }
        clearinspection();
    }

    private void clearinspection() {
        date1.setValue(null);
        date2.setValue(null);
        date3.setValue(null);
        inspectionDate.setValue(null);
        answer1.setValue(null);
        answer2.setValue(null);
        answer3.setValue(null);
        empInspectionQuestion1.setValue(null);
        empInspectionQuestion2.setValue(null);
        empInspectionQuestion3.setValue(null);
        employeeTF.setValue(null);
        descriptionText1.clear();
        descriptionText2.clear();
        descriptionText3.clear();
        inspectionDescription.clear();
        setVisibilityInternal();
    }

    private void displayEmployeeComboBox() {
        try {
            List<Employee> dbEmployees = DBManagment.getEmployees();
            System.out.println(dbEmployees);
            List<Employee> facilityEmployees = DBManagment.getEmployeesByFacilityID(selectedFacility.getId());
            System.out.println(facilityEmployees);
            List<Employee> diff = dbEmployees.stream()
                    .filter(e -> !(facilityEmployees.contains(e)))
                    .collect(Collectors.toList());
            System.out.println(diff);

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private String employeeComboboxToString(Employee employee) {
        return employee.getFirstName() + " " + employee.getLastName() + " " + employee.getId();
    }


    public void addHoliday(ActionEvent actionEvent) {
        if (FieldValidation.validateFloatingNum(holidayProceeds) && FieldValidation.validateComboBox(holidayName) &&
                FieldValidation.validateComboBox(holidayEmployee) && FieldValidation.validateDatePicker(holidayDate)) {
            Holiday holidayToAdd = new Holiday();
            holidayToAdd.setName(holidayName.getSelectionModel().getSelectedItem());
            holidayToAdd.setProceeds(Double.parseDouble(holidayProceeds.getText()));
            holidayToAdd.setEmployee(holidayEmployee.getSelectionModel().getSelectedItem());
            holidayToAdd.setDate(holidayDate.getValue());
            holidayToAdd.setFacility(table.getSelectionModel().getSelectedItem());
            if (DBManagment.addHoliday(holidayToAdd)) {
                AlertBox.infoAlert("Uda??o si??!", "Dodano ??wi??to " + holidayToAdd.getName() + ", do pracownika " + holidayToAdd.getEmployee().getFirstName() + ", " + holidayToAdd.getEmployee().getFirstName(), "Obiekt zosta?? dodany do bazy");
                holidayProceeds.clear();
            } else {
                AlertBox.infoAlert("Ups", "Nie uda??o si?? doda?? ??wi??ta", "Obiekt nie zosta?? dodany");
            }
        }
    }

    public void employeeDetails() {
        try {
            ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("employeeDetailsManagerView.fxml")));
        } catch (Exception e) {
            AlertBox.errorAlert("B????d", e.getMessage());
        }
    }

    public void enableEmployeeDataCheck() {
        try {
            if (table.getSelectionModel().getSelectedItem() != null) {
                selectedEmployee = tableEmployee.getSelectionModel().getSelectedItem();
                Global.setEmployee(selectedEmployee);
                employeeInfoButton.setDisable(false);
            }
        } catch (Exception e) {
            AlertBox.errorAlert("B????d", e.getMessage());
        }
    }

    @FXML
    private void setVisibility(ActionEvent actionevent) {
        if (answer1.getSelectionModel().getSelectedItem() != null) {
            if (answer1.getSelectionModel().getSelectedItem().equals("Nie")) {
                setQuestion1Visibility(true);
            } else setQuestion1Visibility(false);
        }
        if (answer2.getSelectionModel().getSelectedItem() != null) {
            if (answer2.getSelectionModel().getSelectedItem().equals("Nie")) {
                setQuestion2Visibility(true);
            } else setQuestion2Visibility(false);
        }
        if (answer3.getSelectionModel().getSelectedItem() != null) {
            if (answer3.getSelectionModel().getSelectedItem().equals("Nie")) {
                setQuestion3Visibility(true);
            } else setQuestion3Visibility(false);
        }
    }

    private void setVisibilityInternal() {
        inspectionQuestionsForm.setVisible(true);
        additionComboBoxes.setVisible(true);
        setQuestion1Visibility(false);
        setQuestion2Visibility(false);
        setQuestion3Visibility(false);
    }

}

