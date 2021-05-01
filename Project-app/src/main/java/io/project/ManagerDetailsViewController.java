package io.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerDetailsViewController implements Initializable {
	
	@FXML
	private AnchorPane employeePane;
	
	@FXML
	private Label employeeFirstName;
	
	@FXML
	private Label employeeLastName;
	
	@FXML
	private Label employeeBirthDate;
	
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/employeeDetailsView.fxml"));
		try {
			Parent root = loader.load();
			employeePane.getChildren().clear();
			employeePane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
