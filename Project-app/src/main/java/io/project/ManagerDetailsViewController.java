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
	private AnchorPane managerPane;
	
	@FXML
	private Label managerFirstName;
	
	@FXML
	private Label managerLastName;
	
	@FXML
	private Label managerBirthDate;
	
	@FXML
	private Label managerPhoneNumber;
	
	@FXML
	private Label managerEmploymentPlace;
	
	@FXML
	private Label managerPosition;
	
	@FXML
	private Label managerSalary;
	
	@FXML
	private Label managerCategory;
	
	@FXML
	private Label managerSOO;
	
	@FXML
	private Label managerCoursesHours;
	
	@FXML
	private ListView<?> managerResponsibilitiesList;
	
	@FXML
	private Button managerConfirmButton;
	
	@FXML
	private Button managerLogoutButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/managerDetailsView.fxml"));
		try {
			Parent root = loader.load();
			managerPane.getChildren().clear();
			managerPane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
