package io.project;

import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.entities.Violation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
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
	private ListView<Violation> employeeResponsibilitiesList;
	
	@FXML
	private Button employeeConfirmButton;

	
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
		ObservableList<Violation> data = DBManagment.getViolationInfo(currUser);
		employeeResponsibilitiesList.setItems(data);
		employeeResponsibilitiesList.setCellFactory(new Callback<>() {
			@Override
			public ListCell<Violation> call(ListView<Violation> param) {
				return new ListCell<>() {
					@Override
					protected void updateItem(Violation item, boolean empty) {
						if(item != null) {
							super.updateItem(item, empty);
							if (item != null) {
								if (item.getCorrectionDate().isEqual(LocalDate.of(1970, 1, 1))) {
									setDisable(false);
								} else {
									setDisable(true);
								}
								setText(item.toString());
							}
						}
					}
				};
			}
		});
	}
	public void approveResponsibility(){
		Violation selected = employeeResponsibilitiesList.getSelectionModel().getSelectedItem();
		if(DBManagment.approveResponsibility(selected)) {
			ObservableList<Violation> data = DBManagment.getViolationInfo(Global.getCurrentUser());
			employeeResponsibilitiesList.setItems(data);
			Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
			alert1.setTitle("Uda??o si??!");
			alert1.setHeaderText("Zatwierdzono pomy??lnie!");
			alert1.showAndWait();
		}else{
			Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
			alert1.setTitle("B????d!");
			alert1.setHeaderText("Nie uda??o si?? zatwierdzi??!");
			alert1.showAndWait();
		}

	}
}
