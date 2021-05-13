package io.project;

import io.project.entities.Employee;
import io.project.entities.Violation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AccountantDetailsViewController implements Initializable {
	
	@FXML
	private AnchorPane accountantPane;
	
	@FXML
	private Label accountantFirstName;
	
	@FXML
	private Label accountantLastName;
	
	@FXML
	private Label accountantBirthDate;
	
	@FXML
	private Label accountantPhoneNumber;
	
	@FXML
	private Label accountantEmploymentPlace;
	
	@FXML
	private Label accountantPosition;
	
	@FXML
	private Label accountantSalary;
	
	@FXML
	private Label accountantCategory;
	
	@FXML
	private Label accountantSOO;
	
	@FXML
	private Label accountantCoursesHours;
	
	@FXML
	private Label accountantCompanyAccountBalance;
	
	@FXML
	private ListView<Violation> accountantResponsibilitiesList;
	
	@FXML
	private Button accountantConfirmButton;
	
	@FXML
	private Button accountantLogoutButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Employee currUser = Global.getCurrentUser();
		accountantFirstName.setText(currUser.getFirstName());
		accountantLastName.setText(currUser.getLastName());
		accountantBirthDate.setText(currUser.getDOB().format(formatter));
		accountantPhoneNumber.setText(currUser.getPhone());
		accountantPosition.setText(currUser.getPosition());
		accountantCategory.setText(currUser.getCategory());
		accountantSalary.setText(currUser.getSalary());
		accountantSOO.setText(currUser.getPPE().format(formatter));
		accountantCoursesHours.setText(String.valueOf(currUser.getCourseHoursSum()));
	}
}
