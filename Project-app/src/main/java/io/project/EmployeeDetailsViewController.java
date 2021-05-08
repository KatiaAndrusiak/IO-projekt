package io.project;

import io.project.entities.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EmployeeDetailsViewController implements Initializable {
	
	@FXML
	private AnchorPane employeePane;
	
	@FXML
	private Label employeeFirstName;
	
	@FXML
	private Label employeeLastName;
	
	@FXML
	private Label employeeBirthDate;
	
	@FXML
	private Label employeeEmploymentDate;
	
	@FXML
	private Label employeePhoneNumber;
	
	@FXML
	private Label employeePosition;
	
	@FXML
	private Label employeeSalary;
	
	@FXML
	private Label employeeCategory;
	
	@FXML
	private Label employeeSOO;
	
	@FXML
	private Label employeeCoursesHours;
	
	@FXML
	private ListView<?> employeeResponsibilitiesList;
	
	@FXML
	private Button employeeConfirmButton;
	
	@FXML
	private Button employeeLogoutButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Employee currUser = Global.getCurrentUser();
		employeeFirstName.setText(currUser.getFirstName());
		employeeLastName.setText(currUser.getLastName());
		employeeBirthDate.setText(currUser.getDOB().format(formatter));
		employeePhoneNumber.setText(currUser.getPhone());
		employeeEmploymentDate.setText(currUser.getEmploymentDate().format(formatter));
		employeePosition.setText(currUser.getPosition());
		employeeCategory.setText(currUser.getCategory());
		employeeSalary.setText(currUser.getSalary());
		employeeSOO.setText(currUser.getPPE().format(formatter));
		employeeCoursesHours.setText(String.valueOf(currUser.getCourseHoursSum()));
	}
}
