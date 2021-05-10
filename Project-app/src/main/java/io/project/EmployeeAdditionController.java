package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.validation.CheckAndClearTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

	public void addEmployee(ActionEvent event) {
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
		if (DBManagment.addEmployee(userToAdd)) {
			AlertBox.infoAlert("Udało się!", "Pracownik " + userToAdd.getFirstName() + " " + userToAdd.getLastName() + " został dodany do bazy", "Pracownik został dodany do bazy");
			CheckAndClearTextField.clearTextField(employeeFirstNameTF);
			CheckAndClearTextField.clearTextField(employeeLastNameTF);
			CheckAndClearTextField.clearTextField(employeeUsernameTF);
			CheckAndClearTextField.clearTextField(employeePasswordTF);
			CheckAndClearTextField.clearTextField(employeeSalaryTF);
			CheckAndClearTextField.clearTextField(employeePhoneTF);
			CheckAndClearTextField.clearTextField(employeeCoursesHoursTF);
		}
	}


	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DBManagment.setEmployeeAdditionData(employeePosition, employeeRole, employeeCategory);
	}
}
