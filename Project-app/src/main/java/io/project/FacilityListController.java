package io.project;

import io.project.entities.Employee;
import io.project.entities.Facility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
	
	// po kliknieciu addButtonHoliday ma sie pokazac holidayForm,
	// po kliknieciu listInspectionButton ma sie pokazac inspectionForm i inspectionQuestionsForm
	// po kliknieciu addEmployeeButton ma sie pokazac employeeForm
}
