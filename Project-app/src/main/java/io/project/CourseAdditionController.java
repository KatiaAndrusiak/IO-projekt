package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Course;
import io.project.validation.CheckAndClearTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
		/*FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/courseAddition.fxml"));
		try {
			Parent root = loader.load();
			courseAdditionPane.getChildren().clear();
			courseAdditionPane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	public void addCourseToEmployee(ActionEvent event) {
		try {
			Course courseToAdd = new Course();
			courseToAdd.setName(courseNameTF.getText());
			courseToAdd.setHours(Integer.parseInt(courseHoursTF.getText()));
			courseToAdd.setEmployee(Global.getEmployee());
			courseToAdd.setDate(courseDateTF.getValue());
			if (DBManagment.addCourseToEmployee(courseToAdd)) {
				AlertBox.infoAlert("Udało się!", "Dodano kurs " + courseToAdd.getName() + ", do pracownika " + courseToAdd.getEmployee().getFirstName() + ", " + courseToAdd.getEmployee().getFirstName() , "Obiekt został dodany do bazy");
				CheckAndClearTextField.clearTextField(courseNameTF);
				CheckAndClearTextField.clearTextField(courseHoursTF);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Błąd!");
			alert.setHeaderText("Podaj ilość godzin poprawnie!");
			alert.showAndWait();
			return;
		}

		//courseName.setVisible(false);
		//courseHours.setVisible(false);
		//courseAddButton.setVisible(false);
	}
}
