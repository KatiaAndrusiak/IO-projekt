package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.screenloader.ChangeScreen;
import io.project.validation.FieldValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeAdditionController implements Initializable
{
	@FXML
	private AnchorPane employeeAdditionPane;
	
	@FXML
	private TextField employeeFirstNameTF;
	
	@FXML
	private TextField employeeLastNameTF;
	
	@FXML
	private DatePicker employeeDOBTF;
	
	@FXML
	private DatePicker employmentDate;
	
	@FXML
	private ComboBox<String> employeePosition;
	
	@FXML
	private TextField employeeUsernameTF;
	
	@FXML
	private PasswordField employeePasswordTF;
	
	@FXML
	private TextField employeeSalaryTF;
	
	@FXML
	private TextField employeePhoneTF;
	
	@FXML
	private ComboBox<String> employeeRole;
	
	@FXML
	private ComboBox<String> employeeCategory;
	
	@FXML
	private DatePicker employeeSOO;
	
	@FXML
	private TextField employeeCoursesHoursTF;

	public void addEmployee(ActionEvent event) throws IOException {
		Employee userToAdd = new Employee();
		userToAdd.setFirstName(employeeFirstNameTF.getText());
		userToAdd.setLastName(employeeLastNameTF.getText());
		userToAdd.setUsername(employeeUsernameTF.getText());
		userToAdd.setPassword(employeePasswordTF.getText());
		userToAdd.setDOB(employeeDOBTF.getValue());
		userToAdd.setEmploymentDate(employmentDate.getValue());
		userToAdd.setSalary(employeeSalaryTF.getText());
		userToAdd.setPhone(employeePhoneTF.getText());
		userToAdd.setPosition(employeePosition.getSelectionModel().getSelectedItem());
		userToAdd.setRole(employeeRole.getSelectionModel().getSelectedItem());
		userToAdd.setCategory(employeeCategory.getSelectionModel().getSelectedItem());
		userToAdd.setPPE(employeeSOO.getValue());
		userToAdd.setCourseHoursSum(Integer.parseInt(employeeCoursesHoursTF.getText()));
		Global.setEmployee(userToAdd);
		if (FieldValidation.validateFirstAndLastName(employeeFirstNameTF) && FieldValidation.validateFirstAndLastName(employeeLastNameTF) &&
			FieldValidation.validateDatePicker(employeeDOBTF) && FieldValidation.validateDatePicker(employmentDate) && FieldValidation.validateDatePicker(employeeSOO) &&
			FieldValidation.validatePhoneNumber(employeePhoneTF) && FieldValidation.validateCoursesHours(employeeCoursesHoursTF) && FieldValidation.validateNum(employeeSalaryTF) &&
		    FieldValidation.validateLoginAndPassword(employeeUsernameTF) && FieldValidation.validateLoginAndPassword(employeePasswordTF)) {
			if (DBManagment.addEmployee(userToAdd)) {
				AlertBox.infoAlert("Udało się!", "Pracownik " + userToAdd.getFirstName() + " " + userToAdd.getLastName() + " został dodany do bazy", "Pracownik został dodany do bazy");
				employeeFirstNameTF.clear();
				employeeLastNameTF.clear();
				employeeUsernameTF.clear();
				employeePasswordTF.clear();
				employeeSalaryTF.clear();
				employeePhoneTF.clear();
				employeeCoursesHoursTF.clear();
				ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("employeeList.fxml")));
			}
		}

	}


	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DBManagment.setEmployeeAdditionData(employeePosition, employeeRole, employeeCategory);
		FieldValidation.filterForNumberFields(employeeSalaryTF);
		FieldValidation.filterForNumberFields(employeePhoneTF);
		FieldValidation.filterForNumberFields(employeeCoursesHoursTF);
		FieldValidation.filterForTextFields(employeeFirstNameTF);
		FieldValidation.filterForTextFields(employeeLastNameTF);
		employeePosition.getSelectionModel().select(2);
		employeeRole.getSelectionModel().select(2);
		employeeCategory.getSelectionModel().selectFirst();
	}
}
