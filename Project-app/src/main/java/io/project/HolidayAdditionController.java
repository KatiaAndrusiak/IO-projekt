package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Employee;
import io.project.entities.Holiday;
import io.project.validation.CheckAndClearTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HolidayAdditionController implements Initializable
{
	@FXML
	private AnchorPane holidayAdditionPane;
	
	@FXML
	private ComboBox<String> holidayNameComboBox;
	
	@FXML
	private DatePicker holidayDateTF;
	
	@FXML
	private TextField holidayIncomeTF;
	
	@FXML
	private ComboBox<Employee> holidayPersonComboBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/holidayAddition.fxml"));
		try {
			Parent root = loader.load();
			holidayAdditionPane.getChildren().clear();
			holidayAdditionPane.getChildren().addAll(root);
			DBManagment.fillHolidayAdditionData(holidayPersonComboBox);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		DBManagment.fillHolidayAdditionDataEmployee(holidayPersonComboBox);
		DBManagment.fillHolidayAdditionDataHoliday(holidayNameComboBox);
	}

    public void addHoliday(ActionEvent actionEvent) {
		Holiday holidayToAdd = new Holiday();
		holidayToAdd.setName(holidayNameComboBox.getSelectionModel().getSelectedItem());
		holidayToAdd.setProceeds(Double.parseDouble(holidayIncomeTF.getText()));
		holidayToAdd.setEmployee(holidayPersonComboBox.getSelectionModel().getSelectedItem());
		holidayToAdd.setDate(holidayDateTF.getValue());
		holidayToAdd.setFacility(Global.getFacility());
		if (DBManagment.addHoliday(holidayToAdd)) {
			AlertBox.infoAlert("Udało się!", "Dodano święto " + holidayToAdd.getName() + ", do pracownika " + holidayToAdd.getEmployee().getFirstName() + ", " + holidayToAdd.getEmployee().getFirstName() , "Obiekt został dodany do bazy");
			CheckAndClearTextField.clearTextField(holidayIncomeTF);
		}else{
			AlertBox.infoAlert("Ups", "Nie udało się dodać święta", "Obiekt nie został dodany");
		}
    }
}
