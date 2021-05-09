package io.project;

import io.project.database.DBManagment;
import io.project.entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeDetailsManagerViewController implements Initializable {
	
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
	private Button employeeDeleteButton;
	
	@FXML
	private Button employeeAddCourseButton;
	
	@FXML
	private Label employeeFirstName1;
	
	@FXML
	private ListView<?> employeeResponsibilitiesList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	/*	FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/employeeDetailsManagerView.fxml"));
		try {
			Parent root = loader.load();
			employeePane.getChildren().clear();
			employeePane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	public void deleteEmployee(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Usuwanie pracownika");
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
		Employee employeeToDelete = Global.getEmployee();
		alert.setHeaderText("Czy napewno usunac pracownika " + employeeToDelete.getFirstName()+" "+employeeToDelete.getLastName());
		alert.showAndWait();
		System.out.println(alert.getResult() == ButtonType.YES);
		if (alert.getResult() == ButtonType.YES) {
			if(DBManagment.deleteEmployee(employeeToDelete)){
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setTitle("Udało się!");
				alert1.setHeaderText("Pracownik został usunięty!");
				alert1.showAndWait();
			}
		}
	}



}
