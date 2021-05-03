package io.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
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
	private Button CEOLogoutButton;

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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/CEODetailsView.fxml"));
		try {
			Parent root = loader.load();
			CEOPane.getChildren().clear();
			CEOPane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
