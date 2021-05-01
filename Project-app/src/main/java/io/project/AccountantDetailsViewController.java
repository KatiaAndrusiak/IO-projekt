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

public class AccountantDetailsViewController implements Initializable {
	
	@FXML
	private AnchorPane employeePane;
	
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
	private ListView<?> accountantResponsibilitiesList;
	
	@FXML
	private Button accountantConfirmButton;
	
	@FXML
	private Button accountantLogoutButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/accountantDetailsView.fxml"));
		try {
			Parent root = loader.load();
			accountantPane.getChildren().clear();
			accountantPane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
