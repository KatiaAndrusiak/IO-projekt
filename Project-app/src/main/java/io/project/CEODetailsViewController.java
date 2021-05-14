package io.project;

import io.project.entities.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CEODetailsViewController implements Initializable {
	
	@FXML
	private AnchorPane CEOPane;
	
	@FXML
	private Label CEOFirstName;
	
	@FXML
	private Label CEOLastName;
	
	@FXML
	private Label CEOBirthDate;
	
	@FXML
	private Label CEOPhoneNumber;
	
	@FXML
	private Label CEOPosition;
	
	@FXML
	private Label CEOCategory;
	
	@FXML
	private Label CEOCompanyAccountBalance;
	
	@FXML
	private Button CEOConfirmButton;

	@FXML
	private Label CEOCoursesHours;

	@FXML
	private Label CEOSOO;

	@FXML
	private Label CEOSalary;

	@FXML
	private Label CEOEmploymentDate;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Employee currUser = Global.getCurrentUser();
		CEOFirstName.setText(currUser.getFirstName());
		CEOLastName.setText(currUser.getLastName());
		CEOBirthDate.setText(currUser.getDOB().format(formatter));
		CEOPhoneNumber.setText(currUser.getPhone());
		CEOPosition.setText(currUser.getPosition());
		CEOCategory.setText(currUser.getCategory());
	}
}
