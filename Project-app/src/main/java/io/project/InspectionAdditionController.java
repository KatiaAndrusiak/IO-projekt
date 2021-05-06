package io.project;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InspectionAdditionController implements Initializable
{
	@FXML
	private AnchorPane inspectionAdditionPane;
	
	@FXML
	private ComboBox<?> inspectionPersonComboBox;
	
	@FXML
	private DatePicker inspectionDateTF;
	
	@FXML
	private TextField inspectionDescriptionTF;
	
	@FXML
	private ComboBox<?> inspectionQuestion1ComboBox;
	
	@FXML
	private ComboBox<?> inspectionQuestion2ComboBox;
	
	@FXML
	private ComboBox<?> inspectionQuestion3ComboBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/project/inspectionAddition.fxml"));
		try {
			Parent root = loader.load();
			inspectionAdditionPane.getChildren().clear();
			inspectionAdditionPane.getChildren().addAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
