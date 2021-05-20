package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Course;
import io.project.entities.Employee;
import io.project.validation.CheckAndClearTextField;
import io.project.validation.FieldValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseAdditionController implements Initializable
{
	@FXML
	private AnchorPane courseAdditionPane;
	
	@FXML
	private TextField courseNameTF;
	
	@FXML
	private DatePicker courseDateTF;
	
	@FXML
	private TextField courseHoursTF;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	public void addCourseToEmployee(ActionEvent event) {
		try {
			if(FieldValidation.validateCharField(courseNameTF)
					&& FieldValidation.validateNum(courseHoursTF)
			&& FieldValidation.validateDatePicker(courseDateTF)) {
				Course courseToAdd = new Course();
				Employee employee = Global.getEmployee();
				courseToAdd.setName(courseNameTF.getText());
				courseToAdd.setHours(Integer.parseInt(courseHoursTF.getText()));
				courseToAdd.setEmployee(employee);
				courseToAdd.setDate(courseDateTF.getValue());
				if (DBManagment.addCourseToEmployee(courseToAdd)) {
					AlertBox.infoAlert("Udało się!", "Dodano kurs " + courseToAdd.getName() + ", do pracownika " + courseToAdd.getEmployee().getFirstName() + ", " + courseToAdd.getEmployee().getFirstName(), "Obiekt został dodany do bazy");
					employee.setCourseHoursSum(employee.getCourseHoursSum() + courseToAdd.getHours());
					CheckAndClearTextField.clearTextField(courseNameTF);
					CheckAndClearTextField.clearTextField(courseHoursTF);
				} else {
					AlertBox.errorAlert("Błąd", "Nie udalo sie dodac");
				}
			}
		} catch (Exception exception) {
			AlertBox.errorAlert("Błąd", "Wystapil blad");
			return;
		}
	}
}
