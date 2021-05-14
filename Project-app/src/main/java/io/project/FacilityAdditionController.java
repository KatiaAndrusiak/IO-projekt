package io.project;

import io.project.alert.AlertBox;
import io.project.database.DBManagment;
import io.project.entities.Facility;
import io.project.screenloader.ChangeScreen;
import io.project.validation.CheckAndClearTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FacilityAdditionController implements Initializable
{
	@FXML
	private AnchorPane facilityAdditionPane;
	
	@FXML
	private TextField facilityNameTF;
	
	@FXML
	private TextField facilityCityTF;
	
	@FXML
	private TextField facilityAddressTF;
	
	@FXML
	private ComboBox<String> facilityStatusComboBox;

	@FXML
	private TextField facilitySchedule;

	public void addFacility(ActionEvent event) throws IOException {
		Facility facilityToAdd = new Facility();
		facilityToAdd.setName(facilityNameTF.getText());
		facilityToAdd.setAddress(facilityAddressTF.getText());
		facilityToAdd.setStatus(facilityStatusComboBox.getSelectionModel().getSelectedItem());
		facilityToAdd.setSchedule(facilitySchedule.getText());
		facilityToAdd.setCity(facilityCityTF.getText());
		Global.setFacility(facilityToAdd);
		if (DBManagment.addFacility(facilityToAdd)) {
			AlertBox.infoAlert("Udało się!", "Obiekt " + facilityToAdd.getName() + ", który znajduje się pod adresem " + facilityToAdd.getCity() + ", " + facilityToAdd.getAddress() + " został dodany do bazy", "Obiekt został dodany do bazy");
			CheckAndClearTextField.clearTextField(facilityNameTF);
			CheckAndClearTextField.clearTextField(facilityAddressTF);
			CheckAndClearTextField.clearTextField(facilitySchedule);
			CheckAndClearTextField.clearTextField(facilityCityTF);
			ChangeScreen.initPanel(Global.getViewPane(), FXMLLoader.load(getClass().getResource("facilityList.fxml")));
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DBManagment.fillFacilityAdditionData(facilityStatusComboBox);
	}
}
