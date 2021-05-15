package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.entities.Violation;
import io.project.screenloader.ChangeScreen;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
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
	private ListView<Violation> employeeResponsibilitiesList;

	public void setEmployeeDetails(){
		Employee e = Global.getEmployee();
		employeeFirstName.setText(e.getFirstName());
		employeeLastName.setText(e.getLastName());
		employeeBirthDate.setText(e.getDOB().toString());
		employeeEmploymentDate.setText(e.getEmploymentDate().toString());
		employeePhoneNumber.setText(e.getPhone());
		employeePosition.setText(e.getPosition());
		employeeSalary.setText(e.getSalary());
		employeeCategory.setText(e.getCategory());
		employeeSOO.setText(e.getPPE().toString());
		employeeCoursesHours.setText(""+e.getCourseHoursSum());
	}

	public  void addCourseToEmployee(){
		try {
			ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("courseAddition.fxml")));
		}
		catch (Exception e){
			AlertBox.errorAlert("Błąd", e.getMessage());
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setEmployeeDetails();
		ObservableList<Violation> data = DBManagment.getViolationInfo(Global.getEmployee());
		employeeResponsibilitiesList.setItems(data);
		employeeResponsibilitiesList.setCellFactory(new Callback<>() {
			@Override
			public ListCell<Violation> call(ListView<Violation> param) {
				return new ListCell<>() {
					@Override
					protected void updateItem(Violation item, boolean empty) {
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
				};
			}
		});
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
