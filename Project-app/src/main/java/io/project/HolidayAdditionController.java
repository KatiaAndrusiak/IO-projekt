package io.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HolidayAdditionController implements Initializable
{
	@FXML
	private AnchorPane holidayAdditionPane;
	
	@FXML
	private TextField holidayNameTF;
	
	@FXML
	private DatePicker holidayDateTF;
	
	@FXML
	private TextField holidayIncomeTF;
	
	@FXML
	private ComboBox<?> holidayPersonComboBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/holidayAddition.fxml"));
		try {
			Parent root = loader.load();
			holidayAdditionPane.getChildren().clear();
			holidayAdditionPane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
