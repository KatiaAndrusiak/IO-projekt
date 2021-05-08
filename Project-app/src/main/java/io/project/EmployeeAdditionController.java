package io.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
	private TextField employeeAddressTF;
	
	@FXML
	private TextField employeePositionTF;
	
	@FXML
	private TextField employeeUsernameTF;
	
	@FXML
	private PasswordField employeePasswordTF;
	
	@FXML
	private TextField employeeSalaryTF;
	
	@FXML
	private TextField employeePhoneTF;
	
	@FXML
	private TextField employeeRoleTF;
	
	@FXML
	private TextField employeeCategoryTF;
	
	@FXML
	private TextField employeeSOOTF;
	
	@FXML
	private TextField employeeCoursesHoursTF;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/employeeAddition.fxml"));
		try {
			Parent root = loader.load();
			employeeAdditionPane.getChildren().clear();
			employeeAdditionPane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
